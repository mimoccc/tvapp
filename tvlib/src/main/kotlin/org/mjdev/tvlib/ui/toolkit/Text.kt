@file:OptIn(ExperimentalTextApi::class)
@file:Suppress("unused")

package org.mjdev.tvlib.ui.toolkit

import android.content.res.Resources
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.runtime.*
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.buildAnnotatedString

@Immutable
@Stable
sealed interface Text {
    companion object {
        operator fun invoke(
            value: CharSequence
        ): Text = Raw(value)

        operator fun invoke(
            @StringRes id: Int,
            vararg formatArgs: Any
        ): Text = StringResource2(id, formatArgs)

        operator fun invoke(
            @StringRes id: Int,
            isHtml: Boolean = false
        ): Text = if (isHtml) HtmlResource(id) else StringResource(id)

        operator fun invoke(
            @PluralsRes id: Int,
            quantity: Int
        ): Text = PluralResource(packInts(id, quantity))

        operator fun invoke(
            id: Int,
            quantity: Int,
            vararg formatArgs: Any
        ): Text = PluralResource2(id, quantity, formatArgs)
    }
}

@JvmInline
@Immutable
private value class Raw(val value: CharSequence) : Text

@JvmInline
@Immutable
private value class StringResource(val id: Int) : Text

private data class StringResource2(
    val id: Int,
    val formatArgs: Array<out Any>
) : Text {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as StringResource2
        if (id != other.id) return false
        return formatArgs.contentEquals(other.formatArgs)
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + formatArgs.contentHashCode()
        return result
    }
}

@JvmInline
@Immutable
private value class HtmlResource(@StringRes val id: Int) : Text

@JvmInline
@Immutable
private value class PluralResource(val packedValue: Long) : Text {
    @Stable
    val id: Int
        @PluralsRes
        get() = unpackInt1(packedValue)

    @Stable
    val quantity: Int
        get() = unpackInt2(packedValue)
}

private data class PluralResource2(
    val id: Int,
    val quantity: Int,
    val formatArgs: Array<out Any>
) : Text {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as PluralResource2
        if (id != other.id) return false
        if (quantity != other.quantity) return false
        return formatArgs.contentEquals(other.formatArgs)
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + quantity
        result = 31 * result + formatArgs.contentHashCode()
        return result
    }
}

val Text.raw: Any
    get() = when (this) {
        is HtmlResource -> id
        is PluralResource -> id
        is Raw -> value
        is StringResource -> id
        is StringResource2 -> id
        is PluralResource2 -> id
    }


val Text.value: CharSequence
    @Composable
    @NonRestartableComposable
    get() = when (this) {
        is HtmlResource -> textResource(id = this.id)
        is PluralResource -> pluralTextResource(
            id = this.id,
            quantity = this.quantity
        )

        is PluralResource2 -> pluralTextResource(
            this.id,
            this.quantity,
            *this.formatArgs
        )

        is Raw -> this.value
        is StringResource -> textResource(id = id)
        is StringResource2 -> textResource(id, *formatArgs)
    }

inline val Text.get: CharSequence
    @Composable
    @NonRestartableComposable
    inline get() = value

@Composable
@NonRestartableComposable
fun stringResource(
    value: Text
) = value.value

@Composable
@NonRestartableComposable
@JvmName("stringResource1")
fun stringResource(
    value: Text?
) = value?.value

@ExperimentalTextApi
private fun Resources.resolve(
    text: Text
): CharSequence = when (text) {
    is HtmlResource -> getText2(text.id)
    is PluralResource -> getQuantityText2(text.id, text.quantity)
    is PluralResource2 -> getQuantityText2(text.id, text.quantity, *text.formatArgs)
    is Raw -> text.value
    is StringResource -> getText2(text.id)
    is StringResource2 -> getText2(text.id, *text.formatArgs)
}

@JvmName("resolve2")
private fun Resources.resolve(
    text: Text?
): CharSequence? = if (text == null) null else resolve(text)

fun buildText(
    value: String
): Text = Raw(value)

fun buildText(
    value: AnnotatedString
): Text = Raw(value)

fun buildText(
    builder: (AnnotatedString.Builder).() -> Unit
): Text = Raw(buildAnnotatedString(builder))

fun buildTextResource(
    @StringRes id: Int
): Text = StringResource(id)

fun buildTextResource(
    @StringRes id: Int,
    vararg formatArgs: Any
): Text = StringResource2(id, formatArgs)

fun buildHtmlResource(
    @StringRes id: Int
): Text = HtmlResource(id)

fun buildPluralResource(
    @PluralsRes id: Int,
    quantity: Int
): Text = PluralResource(packInts(id, quantity))

fun buildPluralResource(
    id: Int,
    quantity: Int,
    vararg formatArgs: Any
): Text = PluralResource2(id, quantity, formatArgs)
