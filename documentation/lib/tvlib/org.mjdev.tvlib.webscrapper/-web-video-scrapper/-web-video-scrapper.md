//[tvlib](../../../index.md)/[org.mjdev.tvlib.webscrapper](../index.md)/[WebVideoScrapper](index.md)/[WebVideoScrapper](-web-video-scrapper.md)

# WebVideoScrapper

[androidJvm]\
constructor(context: [Context](https://developer.android.com/reference/kotlin/android/content/Context.html), loadImages: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, loadMedia: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, loadScripts: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, loadCssStyles: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, loadFonts: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, isNsfw: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = false, parseUrls: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)&gt;, parsedLinks: [List](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)&lt;[ScrapeLink](../../org.mjdev.tvlib.webscrapper.base/-scrape-link/index.md)&gt; = emptyList(), userAgent: [String](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) = USER_AGENT, onVideoFound: suspend [WebVideoScrapper](index.md).(video: [WebVideoScrapper.Video](-video/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onAudioFound: suspend [WebVideoScrapper](index.md).(audio: [WebVideoScrapper.Audio](-audio/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onNoVideoFound: suspend [WebVideoScrapper](index.md).(link: [ScrapeLink](../../org.mjdev.tvlib.webscrapper.base/-scrape-link/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onNoAudioFound: suspend [WebVideoScrapper](index.md).(link: [ScrapeLink](../../org.mjdev.tvlib.webscrapper.base/-scrape-link/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onLinkParsed: suspend [WebVideoScrapper](index.md).(link: [ScrapeLink](../../org.mjdev.tvlib.webscrapper.base/-scrape-link/index.md)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onFinish: suspend [WebVideoScrapper](index.md).() -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, addBlocker: [IAdBlocker](../../org.mjdev.tvlib.webscrapper.adblock/-i-ad-blocker/index.md) = NoAdBlock(), pauseState: [MutableState](https://developer.android.com/reference/kotlin/androidx/compose/runtime/MutableState.html)&lt;[Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)&gt; = mutableStateOf(false))