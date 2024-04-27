@file:Suppress("unused")

package org.mjdev.tvlib.ui.toolkit

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.io.Closeable
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.RenderVectorGroup
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.PlatformParagraphStyle
import androidx.compose.ui.text.PlatformSpanStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextGeometricTransform
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.style.TextMotion
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import timber.log.Timber

@Composable
fun <T> rememberState(initial: T): MutableState<T> = remember { mutableStateOf(initial) }

val Context.activity: Activity? get() = findActivity()

tailrec fun Context.findActivity(): Activity? =
    when (this) {
        is Activity -> this
        is ContextWrapper -> this.baseContext.findActivity()
        else -> null
    }

@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val activity = context.activity ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            // restore original orientation when view disappears
            activity.requestedOrientation = originalOrientation
        }
    }
}

@Composable
operator fun PaddingValues.plus(value: PaddingValues): PaddingValues {
    val direction = LocalLayoutDirection.current
    return PaddingValues(
        start = this.calculateStartPadding(direction) + value.calculateStartPadding(direction),
        top = this.calculateTopPadding() + value.calculateTopPadding(),
        bottom = this.calculateBottomPadding() + value.calculateBottomPadding(),
        end = this.calculateEndPadding(direction) + value.calculateEndPadding(direction)
    )
}

inline fun <R> runCatching(block: () -> R): R? = try {
    block()
} catch (e: Throwable) {
    Timber.e("runCatching: ${e.message}")
    null
}

inline fun <T : Closeable?, R> T.safeUse(block: (T) -> R): R? = try {
    use { block(it) }
} catch (e: Throwable) {
    Timber.i("use: ${e.message}")
    null
}

suspend inline fun <T> synchronised(
    lock: Mutex,
    action: () -> T
): T = lock.withLock(action = action)


@SuppressLint("ComposableNaming")
@Composable
@ReadOnlyComposable
fun calculate(calculation: () -> Unit) {
    calculation.invoke()
}

inline fun <reified T> castTo(anything: Any): T {
    return anything as T
}

val ProvidableCompositionLocal<Context>.activity
    @ReadOnlyComposable
    @Composable
    get() = current.findActivity()

val ProvidableCompositionLocal<Context>.resources: Resources
    @ReadOnlyComposable
    @Composable
    inline get() = current.resources

fun composableOrNull(
    condition: Boolean,
    content: @Composable () -> Unit
) = when (condition) {
    true -> content
    else -> null
}

inline fun <R : Any> AnnotatedString.Builder.withSpanStyle(
    color: Color = Color.Unspecified,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
    fontStyle: androidx.compose.ui.text.font.FontStyle? = null,
    fontSynthesis: FontSynthesis? = null,
    fontFamily: androidx.compose.ui.text.font.FontFamily? = null,
    fontFeatureSettings: String? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    baselineShift: BaselineShift? = null,
    textGeometricTransform: TextGeometricTransform? = null,
    localeList: LocaleList? = null,
    background: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    shadow: Shadow? = null,
    platformStyle: PlatformSpanStyle? = null,
    drawStyle: DrawStyle? = null,
    block: AnnotatedString.Builder.() -> R
): R = withStyle(
    SpanStyle(
        color,
        fontSize,
        fontWeight,
        fontStyle,
        fontSynthesis,
        fontFamily,
        fontFeatureSettings,
        letterSpacing,
        baselineShift,
        textGeometricTransform,
        localeList,
        background,
        textDecoration,
        shadow,
        platformStyle,
        drawStyle
    ), block
)

inline fun <R : Any> AnnotatedString.Builder.withSpanStyle(
    brush: Brush?,
    alpha: Float = Float.NaN,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
    fontStyle: androidx.compose.ui.text.font.FontStyle? = null,
    fontSynthesis: FontSynthesis? = null,
    fontFamily: androidx.compose.ui.text.font.FontFamily? = null,
    fontFeatureSettings: String? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    baselineShift: BaselineShift? = null,
    textGeometricTransform: TextGeometricTransform? = null,
    localeList: LocaleList? = null,
    background: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    shadow: Shadow? = null,
    platformStyle: PlatformSpanStyle? = null,
    drawStyle: DrawStyle? = null,
    block: AnnotatedString.Builder.() -> R
): R = withStyle(
    SpanStyle(
        brush,
        alpha,
        fontSize,
        fontWeight,
        fontStyle,
        fontSynthesis,
        fontFamily,
        fontFeatureSettings,
        letterSpacing,
        baselineShift,
        textGeometricTransform,
        localeList,
        background,
        textDecoration,
        shadow,
        platformStyle,
        drawStyle
    ), block
)

inline fun <R : Any> AnnotatedString.Builder.withParagraphStyle(
    textAlign: TextAlign = TextAlign.Left,
    textDirection: TextDirection = TextDirection.Ltr,
    lineHeight: TextUnit = TextUnit.Unspecified,
    textIndent: TextIndent? = null,
    platformStyle: PlatformParagraphStyle? = null,
    lineHeightStyle: LineHeightStyle? = null,
    lineBreak: LineBreak = LineBreak.Simple,
    hyphens: Hyphens = Hyphens.None,
    textMotion: TextMotion? = null,
    crossinline block: AnnotatedString.Builder.() -> R
): R = withStyle(
    ParagraphStyle(
        textAlign,
        textDirection,
        lineHeight,
        textIndent,
        platformStyle,
        lineHeightStyle,
        lineBreak,
        hyphens,
        textMotion
    ), block
)

inline fun <R : Any> AnnotatedString.Builder.withStyle(
    style: TextStyle,
    crossinline block: AnnotatedString.Builder.() -> R
): R = withStyle(style.toParagraphStyle()) {
    withStyle(style.toSpanStyle(), block)
}

@Composable
fun rememberVectorPainter(
    image: ImageVector,
    defaultWidth: Dp = image.defaultWidth,
    defaultHeight: Dp = image.defaultHeight,
    viewportWidth: Float = image.viewportWidth,
    viewportHeight: Float = image.viewportHeight,
    name: String = image.name,
    tintColor: Color = image.tintColor,
    tintBlendMode: BlendMode = image.tintBlendMode,
    autoMirror: Boolean = image.autoMirror,
) = rememberVectorPainter(
    defaultWidth = defaultWidth,
    defaultHeight = defaultHeight,
    viewportWidth = viewportWidth,
    viewportHeight = viewportHeight,
    name = name,
    tintColor = tintColor,
    tintBlendMode = tintBlendMode,
    autoMirror = autoMirror,
    content = { _, _ -> RenderVectorGroup(group = image.root) }
)

internal val IsRunningInPreview
    get() = android.os.Build.DEVICE == "layoutlib"