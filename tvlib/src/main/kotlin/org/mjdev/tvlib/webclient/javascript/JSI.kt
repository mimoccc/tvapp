package org.mjdev.tvlib.webclient.javascript

import android.webkit.JavascriptInterface
import org.mjdev.tvlib.webclient.WebClient
import org.mjdev.tvlib.webclient.html.HtmlPage
import timber.log.Timber

@Suppress("SameParameterValue", "MemberVisibilityCanBePrivate")
class JSI(
    val webView: WebClient,
    val onGotHtml : (htmlPage: HtmlPage, loadedResources: List<String>) -> Unit
) {
    val name: String get() = JSI::class.simpleName!!.lowercase()

    init {
        webView.addJavascriptInterface(this, name)
    }

    // ---------------------------------------------------------------------------------------------

    private fun callJavascript(s: String) {
        Timber.e("Calling javascript command: $s")
        webView.loadUrl("javascript:$s")
    }

    // ---------------------------------------------------------------------------------------------

    private fun callJSI(fnc: String, vararg params: String?) = callJavascript(
        StringBuilder().apply {
            append("jsi.$fnc(")
            params.forEach { p ->
                append("$p${if (p == params.last()) "" else ","}")
            }
            append(");")
        }.toString()
    )

    // ---------------------------------------------------------------------------------------------

    fun getHtml() {
        callJSI(
            "onHtml",
            "'<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>'"
        )
    }

    // ---------------------------------------------------------------------------------------------

    @JavascriptInterface
    fun onHtml(html: String?) {
        onGotHtml(
            HtmlPage(
                webView.url,
                html,
                webView.webViewClient.jsi
            ),
            webView.webViewClient.loadedResources
        )
    }

//    @JavascriptInterface
//    fun showToast(text: String?) {
//        onUI {
//            Toast.makeText(webView.context, text.toString(), Toast.LENGTH_LONG)
//        }
//    }

    // ---------------------------------------------------------------------------------------------

//    class Video(val jsi: JSI) {
//        fun play(ve: VideoElement?) {
//            val id = ve?.id
//            val idx = ve?.idx ?: 0
//            if (id == null) {
//                jsi.callJavascript("document.getElementsByTagName(\"video\")[$idx].play();")
//            } else {
//                jsi.callJavascript("document.getElementById(\"$id\").play();")
//            }
//        }
//
//        fun fullScreen(ve: VideoElement?) {
//            val id = ve?.id
//            val idx = ve?.idx ?: 0
//            if (id == null) {
//                jsi.callJavascript("document.getElementsByTagName(\"video\")[$idx].webkitRequestFullscreen();")
//            } else {
//                jsi.callJavascript("var video = document.getElementById(\"$id\").webkitRequestFullscreen();")
//            }
//        }
//    }
}