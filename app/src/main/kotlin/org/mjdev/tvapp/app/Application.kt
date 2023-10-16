/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.app

import dagger.hilt.android.HiltAndroidApp
import org.mjdev.tvlib.application.TvApplication

@HiltAndroidApp
class Application : TvApplication() {

//    private val imageLoader by lazy {
//        ImageLoader.Builder(this)
//            .allowHardware(false)
////            .allowRgb565(true)
//            .addLastModifiedToFileCacheKey(false)
//            .crossfade(false)
//            .diskCachePolicy(CachePolicy.ENABLED)
//            .memoryCachePolicy(CachePolicy.ENABLED)
//            .respectCacheHeaders(false)
//            .networkObserverEnabled(false)
//            .okHttpClient {
//                OkHttpClient.Builder()
//                    .cache(
//                        Cache(
//                            directory = File(
//                                cacheDir,
//                                "http_cache"
//                            ),
//                            maxSize = 1024L * 1024L * 1024L
//                        )
//                    )
//                    .addNetworkInterceptor(HttpLoggingInterceptor().apply {
//                        level = HttpLoggingInterceptor.Level.BODY
//                    })
//                    .addNetworkInterceptor(CacheInterceptor())
//                    .build()
//            }
//            .diskCache {
//                DiskCache.Builder()
//                    .directory(cacheDir.resolve("image_cache"))
//                    .maxSizePercent(0.7)
//                    .build()
//            }
//            .components {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                    add(ImageDecoderDecoder.Factory())
//                } else {
//                    add(GifDecoder.Factory())
//                }
//                add(AlbumArtDecoder.Factory())
//                add(VideoFrameDecoder.Factory())
//                add(SvgDecoder.Factory())
//            }.build()
//    }

//    override fun onCreate() {
//        super.onCreate()
//    }

//    override fun createImageLoader(): ImageLoader = imageLoader
}