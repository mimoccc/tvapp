//[tvlib](../../../index.md)/[org.mjdev.tvlib.webscrapper.base](../index.md)/[CustomWebViewClient](index.md)/[CustomWebViewClient](-custom-web-view-client.md)

# CustomWebViewClient

[androidJvm]\
constructor(loadImages: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, loadScripts: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, loadMedia: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, loadCssStyles: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, loadFonts: [Boolean](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) = true, adBlocker: [IAdBlocker](../../org.mjdev.tvlib.webscrapper.adblock/-i-ad-blocker/index.md) = NoAdBlock(), onError: ([Exception](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-exception/index.html)) -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) = { e -&gt;
        Timber.e(e)
    }, onPageLoadStart: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null, onPageLoadFinish: () -&gt; [Unit](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)? = null)
