package org.mjdev.tvlib.webclient.cache

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.text.TextUtils
import timber.log.Timber
import java.net.URL
import java.util.*

object NetUtils {

    @Suppress("Deprecation")
    fun isConnected(context: Context): Boolean {
        val cm = context.applicationContext
            .getSystemService(Activity.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo.let { info ->
            info != null && info.isConnected
        }
    }

    fun getOriginUrl(referer: String?): String? {
        var ou = referer
        if (TextUtils.isEmpty(ou)) return ""
        try {
            val url = URL(ou)
            val port = url.port
            ou = url.protocol + "://" + url.host + if (port == -1) "" else ":$port"
        } catch (e: Exception) {
          Timber.e(e)
        }
        return ou
    }

    fun multimapToSingle(maps: Map<String, List<String>?>): Map<String, String> {
        val sb = StringBuilder()
        val map: MutableMap<String, String> = HashMap()
        for ((key, values) in maps) {
            sb.delete(0, sb.length)
            if (!values.isNullOrEmpty()) {
                for (v in values) {
                    sb.append(v)
                    sb.append(";")
                }
            }
            if (sb.isNotEmpty()) {
                sb.deleteCharAt(sb.length - 1)
            }
            map[key] = sb.toString()
        }
        return map
    }

}