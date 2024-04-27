@file:Suppress("DEPRECATION")

package org.mjdev.tvlib.ui.toolkit.blur

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.view.View
import android.view.ViewTreeObserver.OnPreDrawListener
import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.runtime.withFrameMillis
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.NativeCanvas
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.LayoutAwareModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.node.invalidateDraw
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.platform.inspectable
import androidx.compose.ui.unit.IntSize
import androidx.core.graphics.withSave
import org.mjdev.tvlib.ui.toolkit.IsRunningInPreview
import org.mjdev.tvlib.ui.toolkit.findActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.math.roundToInt
import kotlin.system.measureTimeMillis

internal infix fun View.relativeTo(other: View): Offset {
    // Temporary array to store coordinates
    val temp = IntArray(2)

    // Get absolute coordinates of this view on the screen
    getLocationOnScreen(temp)
    val (x1, y1) = temp

    // Get absolute coordinates of the other view on the screen
    other.getLocationOnScreen(temp)
    val (x2, y2) = temp

    // Calculate relative offset by subtracting coordinates
    val xOffset = (x1 - x2).toFloat()
    val yOffset = (y1 - y2).toFloat()

    // Create and return the Offset object
    return Offset(xOffset, yOffset)
}

private class RsBlurNode(
    var radius: Float = 25f,
    var downscale: Float = 1.0f,
) : Modifier.Node(),
    DrawModifierNode,
    CompositionLocalConsumerModifierNode,
    LayoutAwareModifierNode,
    OnPreDrawListener {
    override val shouldAutoInvalidate: Boolean get() = false

    // Required global values for blurring the bitmap
    // A RenderScript instance used to create and execute the blur script
    private lateinit var rs: RenderScript
    private lateinit var rsBlurScript: ScriptIntrinsicBlur

    // An Allocation that holds the output/input bitmap of the blur effect,
    // initialized with the changing bounds of the bitmap.
    private lateinit var outAllocation: Allocation
    private lateinit var inAllocation: Allocation

    // The bitmap used to draw on the screen;
    // this bitmap is not initialized if [bounds] is zero.
    private lateinit var bitmap: Bitmap

    // The associated canvas for drawing on the bitmap
    private var canvas = NativeCanvas()

    // The view associated with this node
    private lateinit var view: View

    // The root window for of this app
    private val window get() = view.context.findActivity()?.window!!

    // The Bounds of this composable wrt to Backdrop.
    private lateinit var bounds: Rect

    /**
     * Flag indicating weather the dependencies of this node are initialized.
     */
    val isReady get() = this@RsBlurNode::bounds.isInitialized && !bounds.isEmpty

    // The job to blur the bitmap
    private var job: Job? = null

    /**
     * Initializes the RenderScript generic dependencies.
     */
    @SuppressLint("SuspiciousCompositionLocalModifierRead")
    override fun onAttach() {
        // Get the current view from the local composition.
        // TODO: Maybe use the observable way to capture this.
        view = currentValueOf(LocalView)
        // Create a RenderScript instance from the current composition's context.
        rs = RenderScript.create(view.context)

        // Create a ScriptIntrinsicBlur instance using the RenderScript instance and the element type.
        rsBlurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
    }

    fun reset() {
        view.viewTreeObserver.removeOnPreDrawListener(this)
        job?.cancel()
        // If bounds are empty, no need to continue.
        if (bounds.isEmpty)
            return
        Timber.d("reset: $bounds $downscale")
        // Recreate the bitmap with adjusted dimensions based on the current configuration.
        bitmap = Bitmap.createBitmap(
            (bounds.width * downscale).roundToInt(),
            (bounds.height * downscale).roundToInt(),
            Bitmap.Config.ARGB_8888
        )
        // Create Allocation objects from the recreated bitmap for the blur effect.
        inAllocation =
            Allocation.createFromBitmap(
                rs,
                bitmap,
                Allocation.MipmapControl.MIPMAP_NONE,
                Allocation.USAGE_SCRIPT
            )
        outAllocation = Allocation.createTyped(rs, inAllocation.type)
        // Reattach the pre-draw listener for the next blur process.
        view.viewTreeObserver.addOnPreDrawListener(this)
    }

    override fun onPlaced(coordinates: LayoutCoordinates) {
        // calculate new bound
        val new = coordinates.boundsInWindow()
        // Get coordinates of the view relative to the backdrop.
        val (x, y) = view relativeTo window.decorView
        // calculate bounds relative to root
        val relative = Rect(x + new.left, y + new.top, x + new.right, y + new.bottom)
        if (this::bounds.isInitialized && relative == bounds)
            return
        bounds = relative
        // since bounds have changed this causes reset.
        reset()
    }

    // TODO: Create another method that captures the view with PixelCopy because this does not work
    //  when coil is used with allowHardware = true
    private fun capture() {
        canvas.setBitmap(bitmap)
        bitmap.eraseColor(Color.TRANSPARENT)
        canvas.withSave {
            // First, make sure that the subsequent drawing steps are
            // done in the correct coordinates
            if (downscale < 1f)
                scale(downscale, downscale)
            // Translate the canvas to the origin of the view's bounds
            translate(-bounds.left, -bounds.top)
            // Clip the canvas to the view's bounds
            clipRect(bounds.left, bounds.top, bounds.right, bounds.bottom)
            // Draw all the contents of the view to this bitmap
            // This code seems to iterate over all the descendants of the
            // view's root view and draw them on the canvas
            window.decorView.draw(this)
            Timber.d("captured: ")
        }
    }

    private fun blur() {
        // Set the radius and the input of the blur script
        inAllocation.copyFrom(bitmap)
        // If the bitmap size has changed, create a new output allocation
        rsBlurScript.setRadius(radius)
        rsBlurScript.setInput(inAllocation)
        // Apply the blur script to the output allocation
        // Note: Do not use input Allocation in forEach. it will cause visual artifacts on blurred Bitmap
        rsBlurScript.forEach(outAllocation)
        // Copy the output allocation to the output bitmap
        outAllocation.copyTo(this@RsBlurNode.bitmap)
        Timber.d("blurred: ")
    }

    private val runnable: suspend CoroutineScope.() -> Unit = launch@{
        // Skip if the dependencies are not ready.
        if (!isReady) return@launch
        // Remove the listener temporarily to avoid recursive calls.
        view.viewTreeObserver.removeOnPreDrawListener(this@RsBlurNode)
        // Measure the time taken for capturing the content.
        val captureMills = measureTimeMillis(::capture)
        // Measure the time taken for applying the blur effect.
        val blurMills = measureTimeMillis(::blur)
        Timber.d("onPreDraw: CaptureMills: $captureMills BlurMills: $blurMills")
        withFrameMillis { }
        invalidateDraw()
        view.viewTreeObserver.addOnPreDrawListener(this@RsBlurNode)
    }

    /**
     * Callback triggered before the view is drawn.
     * This is used to capture and blur the content efficiently.
     *
     * @return `true` to proceed with the drawing of this pass, `false` otherwise.
     */
    // TODO - Find something that is good for compose
    override fun onPreDraw(): Boolean {
        // Just return if the blur algorithm is already running.
        if (job?.isActive == true) return false
        job?.cancel()
        job = coroutineScope.launch(block = runnable)
        // Indicate whether to proceed with the drawing of this pass.
        return true
    }

    override fun ContentDrawScope.draw() {
        // Skip drawing any content for this component while blurring,
        // as we only need the content behind it.
        if (!isReady /*|| job?.isActive == true*/) return
        // Draw the blurred image on the canvas.
        drawImage(
            bitmap.asImageBitmap(),
            dstSize = IntSize(size.width.roundToInt(), size.height.roundToInt())
        )
        // Log the drawing of the blurred image for debugging purposes.
        Timber.d("draw: bitmap")
        // Draw any additional content on top of the blurred image.
        drawContent()
    }

    /**
     * Detaches the OnPreDrawListener and releases RenderScript resources when the node is detached.
     */
    override fun onDetach() {
        job?.cancel()
        view.viewTreeObserver.removeOnPreDrawListener(this)
        rs.destroy()
        rsBlurScript.destroy()
        if (this::outAllocation.isInitialized)
            outAllocation.destroy()
        if (this::inAllocation.isInitialized)
            inAllocation.destroy()
    }
}

private data class RsBlurElement(
    var radius: Float,
    var downscale: Float
) : ModifierNodeElement<RsBlurNode>() {

    override fun create(): RsBlurNode =
        RsBlurNode(radius, downscale)

    override fun update(node: RsBlurNode) {
        node.radius = radius
        if (node.downscale != downscale) {
            node.downscale = downscale
            node.reset()
        }
        //TODO -  Call reset manually maybe.
        Timber.d("onUpdate: $node")
    }

    override fun InspectorInfo.inspectableProperties() {
        name = "RsBlur"
        properties["radius"] = radius
        properties["downscale"] = downscale
    }
}

fun Modifier.legacyBackgroundBlur(
    @FloatRange(from = 0.0, to = 25.0) radius: Float = 25f,
    @FloatRange(from = 0.0, to = 1.0, fromInclusive = false) downSample: Float = 1.0f,
) = this then if (IsRunningInPreview) scrimModifier(radius, downSample)
else RsBlurElement(radius, downSample)

private fun Modifier.scrimModifier(
    radius: Float,
    downSample: Float,
    scrimColor: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.Black
) = inspectable(
    inspectorInfo = debugInspectorInfo {
        name = "legacyBackgroundBlur"
        properties["radius"] = radius
        properties["downSample"] = downSample
    },
    factory = {
        background(scrimColor.copy(0.4f))
    }
)
