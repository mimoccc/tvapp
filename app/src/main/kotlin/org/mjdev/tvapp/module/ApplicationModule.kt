/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.module

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import org.mjdev.tvapp.BuildConfig
import org.mjdev.tvapp.database.DAO

val ApplicationModule = DI.Module("ApplicationModule") {
    bindSingleton<DAO> { DAO(instance(), BuildConfig.DEBUG) }
}