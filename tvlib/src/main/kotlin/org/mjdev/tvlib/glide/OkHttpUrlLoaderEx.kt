/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.glide

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.Keep
import com.bumptech.glide.integration.okhttp3.OkHttpStreamFetcher
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoader.LoadData
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.mjdev.tvlib.BuildConfig
import java.io.InputStream
import java.util.concurrent.TimeUnit

/**
 * Custom URL loader
 * This loader implements image load debugging availability
 * @property factory Factory
 * @property nonDebugClient OkHttpClient
 * @property debugClient OkHttpClient?
 * @property client OkHttpClient
 * @property context Context
 * @constructor
 */
@Keep
class OkHttpUrlLoaderEx(
    private val factory: Factory
) : ModelLoader<GlideUrl, InputStream>, SharedPreferences.OnSharedPreferenceChangeListener {
    private val nonDebugClient: OkHttpClient = OkHttpClient.Builder().apply {
        readTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
        connectTimeout(30, TimeUnit.SECONDS)
        followRedirects(true)
        followSslRedirects(true)
    }.build()

    /**
     * Client that make requests debug info
     */
    private var debugClient: OkHttpClient? = null

    /**
     * Standard client, not making any info
     */
    private val client: OkHttpClient?
        get() = if (BuildConfig.DEBUG) debugClient else nonDebugClient

    /**
     * Context to be used
     */
    val context: Context get() = factory.context

    init {
//        context.sharedPrefs.preferences.registerOnSharedPreferenceChangeListener(this)
        initialize()
    }

    /**
     * Initialize loader
     */
    private fun initialize() {
        debugClient = OkHttpClient.Builder().apply {
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            connectTimeout(30, TimeUnit.SECONDS)
            followRedirects(true)
            followSslRedirects(true)
            addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.HEADERS
                )
            )
//            addInterceptor(factory.chuckInterceptor)
        }.build()
    }

    /**
     * De-initialize loader
     */
    fun teardown() {
//        context.sharedPrefs.preferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    /**
     * Preferences change handler, to enable/disable debugging
     * @param sharedPreferences SharedPreferences
     * @param key String
     */
    override fun onSharedPreferenceChanged(
        sharedPreferences: SharedPreferences?,
        key: String?
    ) {
//        if (key == context.sharedPrefs.getName(R.id.settings_log_http_include_images)) {
//            initialize()
//        }
    }

    /**
     * Checker function, see glide library details
     * @param url GlideUrl
     * @return Boolean
     */
    override fun handles(url: GlideUrl): Boolean {
        return true
    }

    /**
     * Prepare loading
     * @param model GlideUrl
     * @param width Int
     * @param height Int
     * @param options Options
     * @return LoadData<InputStream?>
     */
    override fun buildLoadData(
        model: GlideUrl,
        width: Int,
        height: Int,
        options: Options
    ): LoadData<InputStream?> {
        return LoadData(model, OkHttpStreamFetcher(client, model))
    }

    /**
     * Custom factory
     * @property context Context
     * @property factory OkHttpUrlLoaderEx?
     * @constructor
     */
    class Factory(val context: Context) : ModelLoaderFactory<GlideUrl, InputStream> {
        var factory: OkHttpUrlLoaderEx? = null

        /**
         * Prepare builder
         * @param multiFactory MultiModelLoaderFactory
         * @return ModelLoader<GlideUrl, InputStream>
         */
        @Synchronized
        override fun build(
            multiFactory: MultiModelLoaderFactory
        ): ModelLoader<GlideUrl, InputStream> {
            factory = factory ?: OkHttpUrlLoaderEx(this)
            return factory!!
        }

        /**
         * Destroy builder
         */
        override fun teardown() {
            factory?.teardown()
            factory = null
        }
    }
}