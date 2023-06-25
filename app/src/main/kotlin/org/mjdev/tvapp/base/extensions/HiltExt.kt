/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.extensions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.savedstate.SavedStateRegistryOwner
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.helpers.StateContextWrapper
import org.mjdev.tvapp.base.viewmodel.BaseViewModel
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.full.memberFunctions

object HiltExt {

    @Suppress("LocalVariableName")
    @Composable
    inline fun <reified VM : BaseViewModel> appViewModel(
        viewModelStoreOwner: ViewModelStoreOwner? = null,
        key: String? = null
    ): VM {
        return if (isEditMode())
            createMockModel()
        else {
            val _viewModelStoreOwner = viewModelStoreOwner
                ?: LocalViewModelStoreOwner.current!!
            val factory = createHiltViewModelFactory(
                _viewModelStoreOwner
            )
            viewModel(
                viewModelStoreOwner = _viewModelStoreOwner,
                key = key,
                factory = factory
            )
        }
    }

    // todo
    @Composable
    inline fun <reified VM : BaseViewModel> createMockModel(): VM {
        val context = StateContextWrapper(LocalContext.current)
        return VM::class.memberFunctions.first { fn ->
            fn.name == "mock"
        }.call(VM::class.companionObjectInstance, context) as? VM
            ?: throw (RuntimeException(
                "ViewModel ${
                    VM::class.simpleName
                } does not contain MOCK companion function." +
                    "Please define companion function fun MOCK(context:Context)in this view model."
            ))
    }

    @Composable
    @PublishedApi
    internal fun createHiltViewModelFactory(
        viewModelStoreOwner: ViewModelStoreOwner
    ): ViewModelProvider.Factory? = if (viewModelStoreOwner is NavBackStackEntry) {
        HiltViewModelFactory(
            context = LocalContext.current,
            navBackStackEntry = viewModelStoreOwner
        )
    } else {
        null
    }

    @Suppress("FunctionName")
    private fun HiltViewModelFactory(
        context: Context,
        navBackStackEntry: NavBackStackEntry
    ): ViewModelProvider.Factory? {
        try {
            val activity = context.let {
                var ctx = it
                while (ctx is ContextWrapper) {
                    if (ctx is Activity) {
                        return@let ctx
                    }
                    ctx = ctx.baseContext
                }
                throw IllegalStateException(
                    "Expected an activity context for creating a HiltViewModelFactory for a " +
                        "NavBackStackEntry but instead found: $ctx"
                )
            }
            return createInternal(
                activity,
                navBackStackEntry,
                navBackStackEntry.arguments,
                navBackStackEntry.defaultViewModelProviderFactory,
            )
        } catch (e: Throwable) {
            return null
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun createInternal(
        activity: Activity,
        owner: SavedStateRegistryOwner,
        defaultArgs: Bundle?,
        delegateFactory: ViewModelProvider.Factory
    ): ViewModelProvider.Factory? {
        return dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.createInternal(
            activity,
            delegateFactory
        )
    }

}