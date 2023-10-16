/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.glide.sources.qrcode

import android.content.Context
import android.graphics.drawable.Drawable
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.data.DataFetcher
import com.bumptech.glide.load.model.ModelLoader
import com.bumptech.glide.load.model.ModelLoaderFactory
import com.bumptech.glide.load.model.MultiModelLoaderFactory
import com.bumptech.glide.signature.ObjectKey
import com.google.zxing.BarcodeFormat

@Suppress("unused")
class QRCodeLoaderFactory(
    val context: Context
) : ModelLoaderFactory<QRCodeRes?, Drawable?> {
    private var loader: QRCodeLoader? = null

    override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<QRCodeRes?, Drawable?> {
        if (loader == null) {
            loader = QRCodeLoader(context)
        }
        return loader!!
    }

    override fun teardown() {
        loader = null
    }

    private class QRCodeLoader(
        val context: Context
    ) : ModelLoader<QRCodeRes?, Drawable?> {
        override fun handles(model: QRCodeRes): Boolean = true

        override fun buildLoadData(
            model: QRCodeRes,
            width: Int,
            height: Int,
            options: Options
        ): ModelLoader.LoadData<Drawable?> {
            return ModelLoader.LoadData(
                ObjectKey(model.toString()),
                QRCodeFetcher(context, model, width, height)
            )
        }

        class QRCodeFetcher(
            val context: Context,
            val model: QRCodeRes,
            var width: Int = 0,
            var height: Int = 0
        ) : DataFetcher<Drawable?> {
            override fun cleanup() {}
            override fun cancel() {}

            override fun getDataClass(): Class<Drawable?> {
                @Suppress("UNCHECKED_CAST")
                return Drawable::class.java as Class<Drawable?>
            }

            override fun getDataSource(): DataSource {
                return DataSource.LOCAL
            }

            override fun loadData(
                priority: Priority,
                callback: DataFetcher.DataCallback<in Drawable?>
            ) {
                try {
                    callback.onDataReady(
                        BarcodeDrawable(
                            model.toString(),
                            BarcodeFormat.QR_CODE
                        )
                    )
                } catch (e: Exception) {
                    callback.onLoadFailed(e)
                }
            }
        }
    }

}