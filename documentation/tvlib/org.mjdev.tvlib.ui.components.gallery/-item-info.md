//[tvlib](../../index.md)/[org.mjdev.tvlib.ui.components.gallery](index.md)/[ItemInfo](-item-info.md)

# ItemInfo

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [ItemInfo](-item-info.md)(modifier: [Modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier.html) = Modifier, src: [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = null, visible: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = isEditMode(), backgroundColor: [Color](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/Color.html) = Color.Transparent, metadataRetriever: [MetadataRetriever](../org.mjdev.tvlib.helpers.media/-metadata-retriever/index.md) = rememberMetaDataRetriever(), titleFromItem: () -&gt; [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = {
        (src as? ItemWithTitle&lt;*&gt;)?.title
    }, dateFromItem: () -&gt; [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = {
        (src as? ItemWithDate)?.date ?: &quot;-&quot;
    }, detailsFromItem: () -&gt; [Any](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)? = {
        metadataRetriever.getInfo(src)
    })
