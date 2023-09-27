package org.mjdev.tvlib.webclient.cache

import timber.log.Timber
import java.security.MessageDigest
import java.util.Locale

@Suppress("Deprecation", "unused", "SameParameterValue")
object MD5Utils {

    fun getMD5(message: String): String {
        return getMD5(message, true)
    }

    private fun getMD5(message: String, upperCase: Boolean = true): String {
        var md5str = ""
        try {
            val md = MessageDigest.getInstance("MD5")
            val input = message.toByteArray()
            val buff = md.digest(input)
            md5str = bytesToHex(buff, upperCase)
        } catch (e: Exception) {
            Timber.e(e)
        }
        return md5str
    }

    private fun bytesToHex(bytes: ByteArray, upperCase: Boolean): String {
        val md5str = StringBuffer()
        var digital: Int
        for (i in bytes.indices) {
            digital = bytes[i].toInt()
            if (digital < 0) digital += 256
            if (digital < 16) md5str.append("0")
            md5str.append(Integer.toHexString(digital))
        }
        return if (upperCase) md5str.toString().toUpperCase(Locale.ROOT)
        else md5str.toString().toLowerCase(Locale.ROOT)
    }

}