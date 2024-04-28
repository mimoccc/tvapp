//[tvlib](../../../index.md)/[org.mjdev.tvlib.assets](../index.md)/[AssetDirectory](index.md)

# AssetDirectory

[androidJvm]\
class [AssetDirectory](index.md)(val context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), val path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) : [IAssetObject](../-i-asset-object/index.md)

## Constructors

| | |
|---|---|
| [AssetDirectory](-asset-directory.md) | [androidJvm]<br>constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), path: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) |

## Functions

| Name | Summary |
|---|---|
| [contains](contains.md) | [androidJvm]<br>fun [contains](contains.md)(name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>open operator override fun [contains](contains.md)(element: [IAssetObject](../-i-asset-object/index.md)): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [containsAll](contains-all.md) | [androidJvm]<br>open override fun [containsAll](contains-all.md)(elements: [Collection](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-collection/index.html)&lt;[IAssetObject](../-i-asset-object/index.md)&gt;): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238) | [androidJvm]<br>open operator fun [equals](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#585090901%2FFunctions%2F-1596939238)(other: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)?): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [forEach](../-i-asset-object/index.md#1221907427%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [forEach](../-i-asset-object/index.md#1221907427%2FFunctions%2F-1596939238)(p0: [Consumer](https://developer.android.com/reference/kotlin/java/util/function/Consumer.html)&lt;in [IAssetObject](../-i-asset-object/index.md)&gt;) |
| [get](get.md) | [androidJvm]<br>operator fun [get](get.md)(s: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)): [IAssetObject](../-i-asset-object/index.md) |
| [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [hashCode](../../org.mjdev.tvlib.webscrapper.select/-element-not-found-exception/index.md#1794629105%2FFunctions%2F-1596939238)(): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [isEmpty](is-empty.md) | [androidJvm]<br>open override fun [isEmpty](is-empty.md)(): [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [iterator](iterator.md) | [androidJvm]<br>open operator override fun [iterator](iterator.md)(): [Iterator](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterator/index.html)&lt;[IAssetObject](../-i-asset-object/index.md)&gt; |
| [parallelStream](../../org.mjdev.tvlib.ui.components.page/-pager-scope/index.md#-1592339412%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [parallelStream](../../org.mjdev.tvlib.ui.components.page/-pager-scope/index.md#-1592339412%2FFunctions%2F-1596939238)(): [Stream](https://developer.android.com/reference/kotlin/java/util/stream/Stream.html)&lt;[IAssetObject](../-i-asset-object/index.md)&gt; |
| [spliterator](../-i-asset-object/index.md#1956926474%2FFunctions%2F-1596939238) | [androidJvm]<br>open override fun [spliterator](../-i-asset-object/index.md#1956926474%2FFunctions%2F-1596939238)(): [Spliterator](https://developer.android.com/reference/kotlin/java/util/Spliterator.html)&lt;[IAssetObject](../-i-asset-object/index.md)&gt; |
| [stream](../../org.mjdev.tvlib.ui.components.page/-pager-scope/index.md#135225651%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun [stream](../../org.mjdev.tvlib.ui.components.page/-pager-scope/index.md#135225651%2FFunctions%2F-1596939238)(): [Stream](https://developer.android.com/reference/kotlin/java/util/stream/Stream.html)&lt;[IAssetObject](../-i-asset-object/index.md)&gt; |
| [takeIf](../../org.mjdev.tvlib.extensions/-list-ext/take-if.md) | [androidJvm]<br>fun &lt;[T](../../org.mjdev.tvlib.extensions/-list-ext/take-if.md)&gt; [Iterable](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-iterable/index.html)&lt;[T](../../org.mjdev.tvlib.extensions/-list-ext/take-if.md)&gt;.[takeIf](../../org.mjdev.tvlib.extensions/-list-ext/take-if.md)(n: [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html), condition: [T](../../org.mjdev.tvlib.extensions/-list-ext/take-if.md).() -&gt; [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)): [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[T](../../org.mjdev.tvlib.extensions/-list-ext/take-if.md)&gt; |
| [toArray](../../org.mjdev.tvlib.ui.components.page/-pager-scope/index.md#-1215154575%2FFunctions%2F-1596939238) | [androidJvm]<br>open fun &lt;[T](../../org.mjdev.tvlib.ui.components.page/-pager-scope/index.md#-1215154575%2FFunctions%2F-1596939238) : [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)&gt; [~~toArray~~](../../org.mjdev.tvlib.ui.components.page/-pager-scope/index.md#-1215154575%2FFunctions%2F-1596939238)(p0: [IntFunction](https://developer.android.com/reference/kotlin/java/util/function/IntFunction.html)&lt;[Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[T](../../org.mjdev.tvlib.ui.components.page/-pager-scope/index.md#-1215154575%2FFunctions%2F-1596939238)&gt;&gt;): [Array](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)&lt;[T](../../org.mjdev.tvlib.ui.components.page/-pager-scope/index.md#-1215154575%2FFunctions%2F-1596939238)&gt; |
| [toString](to-string.md) | [androidJvm]<br>open override fun [toString](to-string.md)(): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

## Properties

| Name | Summary |
|---|---|
| [assets](assets.md) | [androidJvm]<br>val [assets](assets.md): [AssetManager](https://developer.android.com/reference/kotlin/android/content/res/AssetManager.html) |
| [context](context.md) | [androidJvm]<br>val [context](context.md): [Context](https://developer.android.com/reference/kotlin/android/content/Context.html) |
| [path](path.md) | [androidJvm]<br>val [path](path.md): [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [size](size.md) | [androidJvm]<br>open override val [size](size.md): [Int](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [type](type.md) | [androidJvm]<br>open override val [type](type.md): [AssetType](../-asset-type/index.md) |
