/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.module

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import org.mjdev.tvapp.viewmodel.DetailViewModel
import org.mjdev.tvapp.viewmodel.IPTVViewModel
import org.mjdev.tvapp.viewmodel.MainViewModel

val ViewModelsModule = DI.Module("ViewModelsModule") {
    bind<MainViewModel>() with singleton { MainViewModel(instance()) }
    bind<DetailViewModel>() with singleton { DetailViewModel(instance()) }
    bind<IPTVViewModel>() with singleton { IPTVViewModel(instance()) }
}