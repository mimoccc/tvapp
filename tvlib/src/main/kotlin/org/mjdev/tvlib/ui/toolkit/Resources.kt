@file:Suppress("unused")

package org.mjdev.tvlib.ui.toolkit

import android.content.res.Resources
import android.graphics.Typeface
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.SubscriptSpan
import android.text.style.SuperscriptSpan
import android.text.style.TypefaceSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import androidx.annotation.ArrayRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.UrlAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat

private inline val TypefaceSpan.toSpanStyle: SpanStyle
    get() = SpanStyle(
        fontFamily = when (family) {
            FontFamily.SansSerif.name -> FontFamily.SansSerif
            FontFamily.Serif.name -> FontFamily.Serif
            FontFamily.Monospace.name -> FontFamily.Monospace
            FontFamily.Cursive.name -> FontFamily.Cursive
            else -> FontFamily.Default
        }
    )

private inline val StyleSpan.toSpanStyle: SpanStyle
    get() {
        return when (style) {
            Typeface.NORMAL -> SpanStyle()
            Typeface.BOLD -> SpanStyle(fontWeight = FontWeight.Bold)
            Typeface.ITALIC -> SpanStyle(fontStyle = FontStyle.Italic)
            Typeface.BOLD_ITALIC -> SpanStyle(
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )

            else -> error("$style not supported")
        }
    }

@OptIn(ExperimentalTextApi::class)
internal fun Spanned.toAnnotatedString() =
    buildAnnotatedString {
        val text = this@toAnnotatedString
        // append the string and
        // then apply the supported annotations.
        append((text.toString()))
        // iterate though each span
        // from the old android and
        // return the annotated version of the
        // corresponding span.
        text.getSpans(0, text.length, Any::class.java).forEach { span ->
            val start = text.getSpanStart(span)
            val end = text.getSpanEnd(span)
            // convert span to corresponding style
            val style = when (span) {
                is StyleSpan -> span.toSpanStyle
                is TypefaceSpan -> span.toSpanStyle
                // whatever size is(dip or pixel) we treat it as sp.
                is AbsoluteSizeSpan -> SpanStyle(fontSize = span.size.sp)
                is RelativeSizeSpan -> SpanStyle(fontSize = span.sizeChange.em)
                is StrikethroughSpan -> SpanStyle(textDecoration = TextDecoration.LineThrough)
                is UnderlineSpan -> SpanStyle(textDecoration = TextDecoration.Underline)
                is SuperscriptSpan -> SpanStyle(baselineShift = BaselineShift.Superscript)
                is SubscriptSpan -> SpanStyle(baselineShift = BaselineShift.Subscript)
                is ForegroundColorSpan -> SpanStyle(color = Color(span.foregroundColor))
                // no idea wh this not works with html
                is BackgroundColorSpan -> SpanStyle(background = Color(span.backgroundColor))
                is URLSpan -> {
                    UrlAnnotation(span.url)
                    SpanStyle(color = Color.SkyBlue, textDecoration = TextDecoration.Underline)
                }

                else -> /*SpanStyle()*/ SpanStyle() // FixMe - unsupported span_ just ignore.
            }
            addStyle(style, start, end)
        }
    }

internal fun CharSequence.format(vararg args: Any): CharSequence {
    // if args is empty early return
    if (args.isEmpty()) return this
    return when (val text = this) {
        is String -> String.format(text, *args)
        is Spanned -> {
            // convert to html
            val html = HtmlCompat.toHtml(text, HtmlCompat.TO_HTML_PARAGRAPH_LINES_INDIVIDUAL)
            // add args
            val formatted = String.format(html, *args)
            // return CharSequence.
            HtmlCompat.fromHtml(formatted, HtmlCompat.FROM_HTML_MODE_COMPACT)
        }

        else -> error("$this type of CharSequence is  not supported")
    }
}

fun Resources.getText(@StringRes id: Int, vararg args: Any): CharSequence =
    getText(id).format(*args)

fun Resources.getQuantityText(@PluralsRes id: Int, quantity: Int, vararg args: Any): CharSequence =
    getQuantityText(id, quantity).format(*args)

fun Resources.getText2(@StringRes id: Int): CharSequence =
    when (val formatted = getText(id)) {
        is String -> formatted
        is Spanned -> formatted.toAnnotatedString()
        else -> error("$formatted is some other type of string.")
    }

fun Resources.getText2(@StringRes id: Int, vararg args: Any): CharSequence =
    when (val formatted = getText(id).format(*args)) {
        is String -> formatted
        is Spanned -> formatted.toAnnotatedString()
        else -> error("$formatted is some other type of string.")
    }

fun Resources.getQuantityText2(@PluralsRes id: Int, quantity: Int): CharSequence =
    when (val text = getQuantityText(id, quantity)) {
        is String -> text
        is Spanned -> text.toAnnotatedString()
        else -> error("$text is some other type of string.")
    }

fun Resources.getQuantityText2(@PluralsRes id: Int, quantity: Int, vararg args: Any): CharSequence =
    when (val text = getQuantityText(id, quantity).format(*args)) {
        is String -> text
        is Spanned -> text.toAnnotatedString()
        else -> error("$text is some other type of string.")
    }

fun Resources.getTextArray2(@ArrayRes id: Int): Array<CharSequence> {
    val array = getTextArray(id)
    for (i in array.indices) {
        array[i] = when (val text = array[i]) {
            is String -> text
            is Spanned -> text.toAnnotatedString()
            else -> error("$text is some other type of string.")
        }
    }
    return array
}

@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

@Composable
@NonRestartableComposable
fun textResource(@StringRes id: Int): CharSequence {
    val resources = resources()
    return when (val formatted = resources.getText(id)) {
        is String -> formatted
        is Spanned -> remember(key1 = id, calculation = formatted::toAnnotatedString)
        else -> error("$formatted is some other type of string.")
    }
}

@Composable
@NonRestartableComposable
fun textResource(@StringRes id: Int, vararg args: Any): CharSequence {
    val resources = resources()
    return when (val formatted = resources.getText(id).format(*args)) {
        is String -> formatted
        is Spanned -> remember(key1 = id, key2 = args, calculation = formatted::toAnnotatedString)
        else -> error("$formatted is some other type of string.")
    }
}

@Composable
@NonRestartableComposable
fun pluralTextResource(@PluralsRes id: Int, quantity: Int, vararg args: Any): CharSequence {
    val resources = resources()
    return when (val text = resources.getQuantityText(id, quantity).format(*args)) {
        is String -> text
        is Spanned -> remember(
            key1 = id,
            key2 = quantity,
            key3 = args,
            calculation = text::toAnnotatedString
        )

        else -> error("$text is some other type of string.")
    }
}

@Composable
@NonRestartableComposable
fun pluralTextResource(@PluralsRes id: Int, quantity: Int): CharSequence {
    val resources = resources()
    return when (val text = resources.getQuantityText(id, quantity)) {
        is String -> text
        is Spanned -> remember(key1 = id, key2 = quantity, calculation = text::toAnnotatedString)
        else -> error("$text is some other type of string.")
    }
}

@Composable
@NonRestartableComposable
fun textArrayResource(@ArrayRes id: Int): Array<CharSequence> {
    val resources = resources()
    return remember(id) {
        resources.getTextArray2(id)
    }
}
