/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("RedundantOverride")

package org.mjdev.tvlib.application

import android.app.Application
import org.mjdev.tvlib.events.EventBusInitializer
import org.mjdev.tvlib.helpers.colors.DynamicColors

open class TvApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        EventBusInitializer.init(this)
    }
}
