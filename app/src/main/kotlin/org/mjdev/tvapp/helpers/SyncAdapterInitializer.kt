/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.helpers

import android.content.Context
import androidx.startup.Initializer

// todo
@Suppress("unused")
class SyncAdapterInitializer : Initializer<Unit> {
    override fun create(context: Context) {
    }
    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}