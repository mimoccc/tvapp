//[tvlib](../../../../index.md)/[org.mjdev.tvlib.helpers.media](../../index.md)/[MetadataRetriever](../index.md)/[Companion](index.md)/[rememberMetaDataRetriever](remember-meta-data-retriever.md)

# rememberMetaDataRetriever

[androidJvm]\

@[Composable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/Composable.html)

fun [rememberMetaDataRetriever](remember-meta-data-retriever.md)(translateMeta: (name: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html), value: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)) -&gt; [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = { n, v -&gt;
                String.format(&quot;%s %s&quot;, n, v)
            }): [MetadataRetriever](../index.md)
