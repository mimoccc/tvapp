/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.events

import android.app.Application
import com.biubiu.eventbus.util.ILogger

object EventBusInitializer {

    lateinit var application: Application

    var logger: ILogger? = null

    fun init(application: Application, logger: ILogger? = null) {
        EventBusInitializer.application = application
        EventBusInitializer.logger = logger
    }

}
