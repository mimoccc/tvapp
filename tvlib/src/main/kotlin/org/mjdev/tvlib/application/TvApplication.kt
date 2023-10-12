/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("RedundantOverride")

package org.mjdev.tvlib.application

import android.app.Application
import coil.ImageLoader
import com.google.android.material.color.DynamicColors

open class TvApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }

    open fun createImageLoader(): ImageLoader? = null
}