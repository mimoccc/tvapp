/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.module

import org.kodein.di.DI

val ApplicationModule = DI.Module("ApplicationModule") {
    import(MainModule)
    import(ViewModelsModule)
}