package org.mjdev.tvlib.webclient.cache

import android.annotation.SuppressLint
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@Suppress("unused")
object TimeUtils {

    private const val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"

    fun formatGMT(time: String): Date? {
        if (time.indexOf("GMT") < 0) {
            try {
                val tt = java.lang.Long.valueOf(time)
                return Date(tt * 1000)
            } catch (e: Exception) {
                Timber.e(e)
            }
            return null
        }
        try {
            return SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US)
                .parse(time.trim { it <= ' ' })
        } catch (e: ParseException) {
            Timber.e(e)
        }
        return null
    }

    fun compare(d1: Date?, d2: Date?): Boolean {
        return if (d1 == null || d2 == null) false
        else d1.time - d2.time > 0
    }

    @SuppressLint("SimpleDateFormat")
    fun getStardTime(time: String?): Date? {
        return time?.let {
            try {
                SimpleDateFormat(DATE_FORMAT).parse(it)
            } catch (e: ParseException) {
                Timber.e(e)
                null
            }
        }
    }

    fun getStardTime(time: Long): Date? {
        try {
            return Date(time * 1000)
        } catch (e: Exception) {
            Timber.e(e)
        }
        return null
    }

    val currentTime: String
        @SuppressLint("SimpleDateFormat")
        get() = SimpleDateFormat(DATE_FORMAT).format(Date())
}