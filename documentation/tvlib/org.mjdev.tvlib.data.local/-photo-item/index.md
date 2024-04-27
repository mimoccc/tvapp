//[tvlib](../../../index.md)/[org.mjdev.tvlib.data.local](../index.md)/[PhotoItem](index.md)

# PhotoItem

[androidJvm]\
class [PhotoItem](index.md) : [Serializable](https://developer.android.com/reference/kotlin/java/io/Serializable.html), [ItemPhoto](../../org.mjdev.tvlib.interfaces/-item-photo/index.md), [ItemWithTitle](../../org.mjdev.tvlib.interfaces/-item-with-title/index.md)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt; , [ItemWithImage](../../org.mjdev.tvlib.interfaces/-item-with-image/index.md)&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt; , [ItemWithBackground](../../org.mjdev.tvlib.interfaces/-item-with-background/index.md)&lt;[Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?&gt; , [ItemWithDescription](../../org.mjdev.tvlib.interfaces/-item-with-description/index.md)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)?&gt; , [ItemWithDate](../../org.mjdev.tvlib.interfaces/-item-with-date/index.md)

## Constructors

| | |
|---|---|
| [PhotoItem](-photo-item.md) | [androidJvm]<br>constructor(c: [Cursor](https://developer.android.com/reference/kotlin/android/database/Cursor.html))constructor() |

## Types

| Name | Summary |
|---|---|
| [Companion](-companion/index.md) | [androidJvm]<br>object [Companion](-companion/index.md) |

## Functions

| Name | Summary |
|---|---|
| [equals](equals.md) | [androidJvm]<br>open operator override fun [equals](equals.md)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | [androidJvm]<br>open override fun [hashCode](hash-code.md)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [toString](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1616463040%2FFunctions%2F-1596939238)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [background](background.md) | [androidJvm]<br>open override var [background](background.md): [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? |
| [date](date.md) | [androidJvm]<br>open override var [date](date.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [description](description.md) | [androidJvm]<br>open override var [description](description.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [hasBackground](../../org.mjdev.tvlib.interfaces/-item-with-background/-companion/has-background.md) | [androidJvm]<br>val &lt;[T](../../org.mjdev.tvlib.interfaces/-item-with-background/-companion/has-background.md)&gt; [ItemWithBackground](../../org.mjdev.tvlib.interfaces/-item-with-background/index.md)&lt;[T](../../org.mjdev.tvlib.interfaces/-item-with-background/-companion/has-background.md)&gt;.[hasBackground](../../org.mjdev.tvlib.interfaces/-item-with-background/-companion/has-background.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hasDate](../../org.mjdev.tvlib.interfaces/-item-with-date/-companion/has-date.md) | [androidJvm]<br>val [ItemWithDate](../../org.mjdev.tvlib.interfaces/-item-with-date/index.md).[hasDate](../../org.mjdev.tvlib.interfaces/-item-with-date/-companion/has-date.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hasDescription](../../org.mjdev.tvlib.interfaces/-item-with-description/-companion/has-description.md) | [androidJvm]<br>val &lt;[T](../../org.mjdev.tvlib.interfaces/-item-with-description/-companion/has-description.md)&gt; [ItemWithDescription](../../org.mjdev.tvlib.interfaces/-item-with-description/index.md)&lt;[T](../../org.mjdev.tvlib.interfaces/-item-with-description/-companion/has-description.md)&gt;.[hasDescription](../../org.mjdev.tvlib.interfaces/-item-with-description/-companion/has-description.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hasImage](../../org.mjdev.tvlib.interfaces/-item-photo/-companion/has-image.md) | [androidJvm]<br>val [ItemPhoto](../../org.mjdev.tvlib.interfaces/-item-photo/index.md).[hasImage](../../org.mjdev.tvlib.interfaces/-item-photo/-companion/has-image.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>val &lt;[T](../../org.mjdev.tvlib.interfaces/-item-with-image/-companion/has-image.md)&gt; [ItemWithImage](../../org.mjdev.tvlib.interfaces/-item-with-image/index.md)&lt;[T](../../org.mjdev.tvlib.interfaces/-item-with-image/-companion/has-image.md)&gt;.[hasImage](../../org.mjdev.tvlib.interfaces/-item-with-image/-companion/has-image.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hasTitle](../../org.mjdev.tvlib.interfaces/-item-with-title/-companion/has-title.md) | [androidJvm]<br>val &lt;[T](../../org.mjdev.tvlib.interfaces/-item-with-title/-companion/has-title.md)&gt; [ItemWithTitle](../../org.mjdev.tvlib.interfaces/-item-with-title/index.md)&lt;[T](../../org.mjdev.tvlib.interfaces/-item-with-title/-companion/has-title.md)&gt;.[hasTitle](../../org.mjdev.tvlib.interfaces/-item-with-title/-companion/has-title.md): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [image](image.md) | [androidJvm]<br>open override var [image](image.md): [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? |
| [title](title.md) | [androidJvm]<br>open override var [title](title.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
| [uri](uri.md) | [androidJvm]<br>open override var [uri](uri.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)? |
