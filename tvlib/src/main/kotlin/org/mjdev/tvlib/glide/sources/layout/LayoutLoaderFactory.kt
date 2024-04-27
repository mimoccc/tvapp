/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.glide.sources.layout

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

/**
 * Inflate view to a drawable thru glide library.
 * Useful for custom drawables.
 * Usage:
 * GlideApp.with(context).load(LayoutResId(R.layout.layout_file)).into(target)
 * @property context Context
 * @property layoutLoader LayoutLoader?
 * @constructor
 */
class LayoutLoaderFactory(val context: Context) : ModelLoaderFactory<LayoutResId?, Drawable?> {

    private var layoutLoader: LayoutLoader? = null

    /**
     * Prepare loader
     * @param multiFactory MultiModelLoaderFactory
     * @return ModelLoader<LayoutResId?, Drawable?>
     */
    override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<LayoutResId?, Drawable?> {
        if (layoutLoader == null) {
            layoutLoader = LayoutLoader(context)
        }
        return layoutLoader!!
    }

    /**
     * Destroy loader
     */
    override fun teardown() {
        layoutLoader = null
    }

    /**
     * Custom class for layout to drawable conversion
     * @property context Context
     * @constructor
     */
    private class LayoutLoader(val context: Context) : ModelLoader<LayoutResId?, Drawable?> {

        /**
         * Handling indicator see glide library description
         * @param model LayoutResId
         * @return Boolean
         */
        override fun handles(model: LayoutResId): Boolean {
            return true
        }

        /**
         * Build loader
         * @param model LayoutResId
         * @param width Int
         * @param height Int
         * @param options Options
         * @return ModelLoader.LoadData<Drawable?>
         */
        override fun buildLoadData(model: LayoutResId, width: Int, height: Int, options: Options): ModelLoader.LoadData<Drawable?> {
            return ModelLoader.LoadData(ObjectKey(model.toString()), LayoutFetcher(context, model, width, height))
        }

        /**
         * Custom drawable fetcher
         * @property context Context
         * @property model LayoutResId
         * @property width Int
         * @property height Int
         * @constructor
         */
        class LayoutFetcher(val context: Context, val model: LayoutResId, var width: Int = 0, var height: Int = 0) : DataFetcher<Drawable?> {
            override fun cleanup() {}
            override fun cancel() {}

            /**
             * Type of data returned
             * @return Class<Drawable?>
             */
            @Suppress("UNCHECKED_CAST")
            override fun getDataClass(): Class<Drawable?> {
                return Drawable::class.java as Class<Drawable?>
            }

            /**
             * Image source
             * @return DataSource
             */
            override fun getDataSource(): DataSource {
                return DataSource.LOCAL
            }

            /**
             * Load image data
             * @param priority Priority
             * @param callback DataCallback<in Drawable?>
             */
            override fun loadData(priority: Priority, callback: DataFetcher.DataCallback<in Drawable?>) {
                try {
                    callback.onDataReady(LayoutInflaterDrawable(context, model.resId))
                } catch (e: Exception) {
                    callback.onLoadFailed(e)
                }
            }
        }

    }

}
