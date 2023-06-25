/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.helpers

import android.content.Context
import android.content.ContextWrapper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner

// todo
class StateContextWrapper(
    baseContext: Context
) : ContextWrapper(baseContext) , LifecycleOwner, ViewModelStoreOwner, SavedStateRegistryOwner {

    private val savedStateRegistryController by lazy {
        SavedStateRegistryController.create(this)
    }

    override val lifecycle: Lifecycle by lazy {
        LifecycleRegistry(this)
    }

    override val viewModelStore: ViewModelStore by lazy {
        ViewModelStore()
    }

    override val savedStateRegistry: SavedStateRegistry
        get() = savedStateRegistryController.savedStateRegistry

}