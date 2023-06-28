/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.navigation

import android.content.Context
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.navigation.NavHostController

// todo apply lifecycle
@Suppress("unused", "MemberVisibilityCanBePrivate")
class NavHostControllerEx(
    context: Context
) : NavHostController(
    context
), LifecycleOwner, DefaultLifecycleObserver {

    private val menuClickListeners = mutableListOf<MenuItemClickListener>()

    override val lifecycle: Lifecycle by lazy { LifecycleRegistry(this) }

    init {
        lifecycle.addObserver(this)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        lifecycle.removeObserver(this)
        clearClickListeners()
    }

    fun addMenuClickListener(listener: MenuItemClickListener) {
        menuClickListeners.add(listener)
    }

    fun removeMenuClickListener(listener: MenuItemClickListener) {
        menuClickListeners.remove(listener)
    }

    fun clearClickListeners() {
        menuClickListeners.clear()
    }

    fun onMenuItemClick(item: MenuItem) {
        menuClickListeners.forEach { l ->
            l.onMenuItemClick(item)
        }
    }

}