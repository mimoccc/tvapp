/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.glide

import android.graphics.drawable.Drawable
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

/**
 * Custom Glide simplified load target
 * @property onLoadStarted Function1<[@kotlin.ParameterName] Drawable?, Unit>? on start handler
 * @property onLoadFinished Function1<[@kotlin.ParameterName] Drawable?, Unit>? on finish handler
 * @constructor
 */
class LoaderTarget(
        width: Int = SIZE_ORIGINAL,
        height: Int = SIZE_ORIGINAL,
        val onLoadStarted: ((drawable: Drawable?) -> Unit)? = null,
        val onLoadFinished: ((drawable: Drawable?) -> Unit)? = null
) : CustomTarget<Drawable>(
        if (width > 0) width else if (height > 0) height else SIZE_ORIGINAL,
        if (height > 0) height else if (width > 0) width else SIZE_ORIGINAL
) {

    @Suppress("unused")
    constructor(
            onLoadStarted: ((drawable: Drawable?) -> Unit)? = null,
            onLoadFinished: ((drawable: Drawable?) -> Unit)? = null
    ) : this(
            SIZE_ORIGINAL,
            SIZE_ORIGINAL,
            onLoadStarted,
            onLoadFinished
    )

    /**
     * Load start handler
     * @param placeholder Drawable?
     */
    override fun onLoadStarted(placeholder: Drawable?) {
        onLoadStarted?.invoke(placeholder)
    }

    /**
     * Load sucessfully finished handler
     * @param resource Drawable
     * @param transition Transition<in Drawable>?
     */
    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
        onLoadFinished?.invoke(resource)
    }

    /**
     * Load stopped or cleared handler
     * @param placeholder Drawable?
     */
    override fun onLoadCleared(placeholder: Drawable?) {
        onLoadFinished?.invoke(placeholder)
    }

    /**
     * Load failed handler
     * @param errorDrawable Drawable?
     */
    override fun onLoadFailed(errorDrawable: Drawable?) {
        onLoadFinished?.invoke(errorDrawable)
    }

}
