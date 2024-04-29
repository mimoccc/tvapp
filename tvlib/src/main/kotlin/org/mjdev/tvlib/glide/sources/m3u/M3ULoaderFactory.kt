/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.glide.sources.m3u

import android.content.Context
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.bumptech.glide.signature.ObjectKey
import java.io.InputStream

// disable m3u loading
@Suppress("UNCHECKED_CAST", "PrivatePropertyName")
class M3ULoaderFactory(
    private val context: Context
) : ModelLoader<String?, InputStream?>, ModelLoaderFactory<String?, InputStream?> {

    override fun buildLoadData(
        source: String,
        width: Int,
        height: Int,
        options: Options
    ): ModelLoader.LoadData<InputStream?> =
        ModelLoader.LoadData<InputStream?>(ObjectKey(source), CoverFetcher(source))

    override fun handles(
        model: String
    ): Boolean = model.isM3UFileOrUrl()

    override fun build(
        multiFactory: MultiModelLoaderFactory
    ): ModelLoader<String?, InputStream?> = M3ULoaderFactory(context)

    override fun teardown() {
    }

    inner class CoverFetcher(
        private val source: String?
    ) : DataFetcher<InputStream?> {

        override fun loadData(
            priority: Priority,
            callback: DataFetcher.DataCallback<in InputStream?>
        ) {
            callback.onLoadFailed(Exception("No cover"))
        }

        override fun cleanup() {
        }

        override fun cancel() {
        }

        override fun getDataClass(): Class<InputStream?> {
            return InputStream::class.java as Class<InputStream?>
        }

        override fun getDataSource(): DataSource {
            return DataSource.LOCAL
        }

    }

}

private fun String.isM3UFileOrUrl(): Boolean = trim().let {
    it.endsWith("m3u") || it.endsWith("m3u8")
}
