package org.mjdev.tvlib.ui.toolkit

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.annotation.FloatRange
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.node.currentValueOf
import androidx.compose.ui.platform.InspectorInfo
import androidx.compose.ui.platform.LocalContext
import org.mjdev.tvlib.R
import timber.log.Timber
import kotlin.system.measureNanoTime

sealed interface ImageBrush {

    companion object {

        fun from(@DrawableRes value: Int): ImageBrush = Resource(value)

        fun from(value: Bitmap): ImageBrush = Image(value.asImageBitmap())


        fun from(value: ImageBitmap): ImageBrush = Image(value)

        val NoiseBrush = from(R.drawable.noise)
    }
}

@JvmInline
private value class Resource(@DrawableRes val id: Int) : ImageBrush

@JvmInline
private value class Image(val bitmap: ImageBitmap) : ImageBrush

private class EffectNode(
    var brush: ImageBrush,
    var alpha: Float,
    var overlay: Boolean,
    var blendMode: BlendMode
) : Modifier.Node(), DrawModifierNode, CompositionLocalConsumerModifierNode {

    private var shaderBrush: ShaderBrush? = null
    private lateinit var lastBrush: ImageBrush

    private fun create(value: ImageBrush): ShaderBrush {
        // Determine the appropriate image source based on the ImageBrush type
        val img = when (value) {
            // Directly use the ImageBitmap from the ImageBrush for direct ImageBitmap sources.
            is Image -> value.bitmap
            is Resource -> {
                // Decode the resource into a Bitmap for resource-based ImageBrushes.
                val resources = currentValueOf(LocalContext).resources
                val bmp: Bitmap
                val time = measureNanoTime {
                    // Decode the resource, disabling scaling for optimal performance.
                    bmp = BitmapFactory.decodeResource(
                        resources,
                        value.id,
                        BitmapFactory.Options().apply {
                            inScaled = false
//                          inTargetDensity = inDensity
//                          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                              this.inPreferredConfig = Bitmap.Config.HARDWARE
//                          }
                        }
                    )
                }
                Timber.d("create: (width=" + bmp.width + ", height=" + bmp.height + ", time=" + time)
                // Convert the decoded Bitmap to an ImageBitmap for use in the ShaderBrush.
                bmp.asImageBitmap()
            }
        }
        // Create and return a ShaderBrush using the ImageShader for repeating the image:
        // TODO - Experiment with different tileModes.
        return ShaderBrush(ImageShader(img, TileMode.Repeated, TileMode.Repeated))
    }

    override fun ContentDrawScope.draw() {
        // Check if the effect should be entirely hidden due to zero alpha:
        if (alpha == 0f) {
            // Simply draw the content without applying any effect.
            drawContent()
            return
        }
        // Check if the cached ShaderBrush needs updated based on brush changes:
        // 1. If no ShaderBrush exists, or
        // 2. Last used brush doesn't match the current brush, or
        // 3. Last brush reference hasn't been initialized yet:
        val needsBrushUpdate =
            shaderBrush == null || lastBrush != brush || !::lastBrush.isInitialized
        if (needsBrushUpdate) {
            // Update the last brush reference and create a new ShaderBrush based on the current brush:
            lastBrush = brush
            shaderBrush = create(brush)
        }
        // Handle drawing order based on the overlay flag:
        if (overlay) {
            // Draw the content now if it's an overlay effect.
            drawContent()
        }

        // Draw the effect rectangle using the prepared ShaderBrush, alpha, and blend mode:
        drawRect(
            brush = shaderBrush!!,
            alpha = alpha,
            blendMode = blendMode,
        )

        if (!overlay) {
            // Draw the content first if it's not an overlay effect.
            drawContent()
        }
    }
}

/**
 * A private [ModifierNodeElement] that holds the properties for the visual effect and creates the corresponding [EffectNode].
 *
 * @param brush The [ImageBrush] for the effect.
 * @param alpha The opacity of the effect.
 * @param overlay Whether the effect is drawn as an overlay.
 * @param blendMode The blend mode for the effect.
 */
private class EffectElement(
    var brush: ImageBrush,
    var alpha: Float,
    var overlay: Boolean,
    var blendMode: BlendMode
) : ModifierNodeElement<EffectNode>() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EffectElement

        if (brush != other.brush) return false
        if (alpha != other.alpha) return false
        if (overlay != other.overlay) return false
        return blendMode == other.blendMode
    }

    override fun hashCode(): Int {
        var result = brush.hashCode()
        result = 31 * result + alpha.hashCode()
        result = 31 * result + overlay.hashCode()
        result = 31 * result + blendMode.hashCode()
        return result
    }

    /**
     * Creates the actual [EffectNode] to be used for drawing the effect.
     */
    override fun create() = EffectNode(brush, alpha, overlay, blendMode)

    /**
     * Updates the properties of an existing [EffectNode] to match this element's configuration.
     */
    override fun update(node: EffectNode) {
        node.blendMode = blendMode
        node.brush = brush
        node.overlay = overlay
        node.alpha = alpha
        Timber.d("update: (brush=$brush, alpha=$alpha, overlay=$overlay, blendMode=$blendMode)")
    }

    /**
     * Provides information for inspection tools to display the properties of this element.
     */
    override fun InspectorInfo.inspectableProperties() {
        name = "EffectElement"
        properties["alpha"] = alpha
        properties["BlendMode"] = blendMode
        properties["Brush"] = brush
        properties["overlay"] = overlay
    }
}

fun Modifier.visualEffect(
    brush: ImageBrush,
    @FloatRange(from = 0.0, to = 1.0) alpha: Float = 1.0f,
    overlay: Boolean = false,
    blendMode: BlendMode = BlendMode.Overlay
): Modifier = this then EffectElement(brush, alpha, overlay, blendMode)