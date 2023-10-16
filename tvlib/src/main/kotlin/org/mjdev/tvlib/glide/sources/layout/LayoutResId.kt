/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.glide.sources.layout

import android.view.View
import androidx.annotation.LayoutRes

/**
 * Inflate view to a drawable thru glide library.
 * Useful for custom drawables.
 * Usage:
 * GlideApp.with(context).load(LayoutResId(R.layout.layout_file)).into(target)
 * @property resId Int
 * @constructor
 */
class LayoutResId(@LayoutRes val resId: Int = View.NO_ID) {
    /**
     * return string representation
     * @return String
     */
    override fun toString(): String {
        return resId.toString()
    }
}