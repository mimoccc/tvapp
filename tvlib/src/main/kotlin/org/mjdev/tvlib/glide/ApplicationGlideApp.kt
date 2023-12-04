/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.graphics.drawable.PictureDrawable
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.caverock.androidsvg.SVG
import org.mjdev.tvlib.BuildConfig
import org.mjdev.tvlib.R
import org.mjdev.tvlib.glide.sources.albumart.AlbumArtLoaderFactory
import org.mjdev.tvlib.glide.sources.layout.LayoutLoaderFactory
import org.mjdev.tvlib.glide.sources.layout.LayoutResId
import org.mjdev.tvlib.glide.sources.svg.SvgDecoder
import org.mjdev.tvlib.glide.sources.svg.SvgDrawableTranscoder
import java.io.InputStream

/**
 * Custom loader for images & predefined settings
 * */
@GlideModule
class ApplicationGlideApp : AppGlideModule() {
    /**
     * Cache size, maximal images cache on device
     */
    private val cacheSize256MegaBytes: Long = 256L * 1024L * 1024L

    private fun calculator(context:Context): MemorySizeCalculator =
        MemorySizeCalculator.Builder(context).build()

    /**
     * Apply custom image loader options
     * @param context Context
     * @param builder GlideBuilder
     */
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.apply {
            val calculator = calculator(context)

            val customMemoryCacheSize = calculator.memoryCacheSize
            val customBitmapPoolSize = calculator.bitmapPoolSize

            builder.setMemoryCache(LruResourceCache(customMemoryCacheSize.toLong()))
            builder.setBitmapPool(LruBitmapPool(customBitmapPoolSize.toLong()))

            setDiskCache(InternalCacheDiskCacheFactory(context, cacheSize256MegaBytes))

            if (BuildConfig.DEBUG)
                setLogLevel(Log.VERBOSE)
            else
                setLogLevel(Log.ERROR)

            setDefaultRequestOptions(
                RequestOptions()
                    .dontAnimate()
                    .dontTransform()
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .downsample(DownsampleStrategy.DEFAULT)
                    .encodeFormat(Bitmap.CompressFormat.PNG)
                    .encodeQuality(100)
                    .placeholder(R.drawable.broken_image)
                    .error(R.drawable.broken_image)
                    .fallback(R.drawable.broken_image)
            )
        }
    }

    /**
     * Register application custom components
     * @param context Context
     * @param glide Glide
     * @param registry Registry
     */
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
        // call registration
        registry.apply {
            // All urls
            replace(
                GlideUrl::class.java,
                InputStream::class.java,
                OkHttpUrlLoaderEx.Factory(context)
            )
            // LayoutRes support (create images from layout res Id)
            prepend(LayoutResId::class.java, Drawable::class.java, LayoutLoaderFactory(context))
            // Album Art audio files
            append(String::class.java, InputStream::class.java, AlbumArtLoaderFactory(context))
            // SVG support
            register(SVG::class.java, PictureDrawable::class.java, SvgDrawableTranscoder())
            append(InputStream::class.java, SVG::class.java, SvgDecoder())
        }
    }
}