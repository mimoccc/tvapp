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
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import dagger.hilt.android.internal.lifecycle.HiltViewModelFactory.createInternal
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import kotlin.reflect.KClass
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.functions

object HiltExt {

    @Suppress("LocalVariableName")
    @Composable
    inline fun <reified VM : ViewModel> appViewModel(
        viewModelStoreOwner: ViewModelStoreOwner? = null,
        key: String? = null,
        mockFnName :String = String.format("mock%s", VM::class.simpleName),
        companion: KClass<*>? = VM::class.companionObject,
        noinline modelMockFnc: ((context: Context) -> VM)? =  { context ->
            companion?.functions?.firstOrNull { fn ->
                fn.name == mockFnName
            }?.call(companion, context) as VM
        }
    ): VM {
        val _viewModelStoreOwner = (viewModelStoreOwner ?: LocalViewModelStoreOwner.current)
            ?: throw (RuntimeException("No view model store."))
        val modelCreator: MutableMap<KClass<*>, (context: Context) -> Any>.() -> Unit = {
            put(VM::class) { context ->
                modelMockFnc?.invoke(context) as VM
            }
        }
        return viewModel(
            viewModelStoreOwner = _viewModelStoreOwner,
            key = key,
            factory = createHiltViewModelFactory(
                _viewModelStoreOwner,
                modelCreator
            ),
        )
    }

    @Composable
    fun createHiltViewModelFactory(
        viewModelStoreOwner: ViewModelStoreOwner,
        modelCreator: MutableMap<KClass<*>, (context: Context) -> Any>.() -> Unit = {}
    ): ViewModelProvider.Factory = hiltViewModelFactory(
        context = LocalContext.current,
        viewModelStoreOwner = viewModelStoreOwner,
        modelCreator = modelCreator
    )

    @Composable
    fun hiltViewModelFactory(
        context: Context,
        viewModelStoreOwner: ViewModelStoreOwner,
        modelCreator: MutableMap<KClass<*>, (context: Context) -> Any>.() -> Unit = {}
    ): ViewModelProvider.Factory {
        val navBackStackEntry: NavBackStackEntry? = viewModelStoreOwner as? NavBackStackEntry
        return if (navBackStackEntry == null || isEditMode())
            createInternal(context, modelCreator)
        else {
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
            createInternal(
                activity,
                navBackStackEntry,
                navBackStackEntry.arguments,
                navBackStackEntry.defaultViewModelProviderFactory,
            )
        }
    }

    private fun createInternal(
        context: Context,
        modelCreator: MutableMap<KClass<*>, (context: Context) -> Any>.() -> Unit
    ): ViewModelProvider.Factory = MockViewModelFactory(context, modelCreator)

    @Suppress("UNCHECKED_CAST")
    class MockViewModelFactory(
        val context: Context,
        modelCreator: MutableMap<KClass<*>, (context: Context) -> Any>.() -> Unit
    ) : ViewModelProvider.Factory {

        private val store = mutableMapOf<KClass<*>, (context: Context) -> Any>()
            .apply(modelCreator)

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return store[modelClass.kotlin]?.invoke(context) as T
        }

        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            return store[modelClass.kotlin]?.invoke(context) as T
        }

    }

}