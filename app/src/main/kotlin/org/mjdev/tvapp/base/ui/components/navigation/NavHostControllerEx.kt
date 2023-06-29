/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.navigation

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.navigation.NavHostController
import org.mjdev.tvapp.base.extensions.ListExt.addUnique

// todo apply lifecycle
@Suppress("unused", "MemberVisibilityCanBePrivate")
class NavHostControllerEx(
    context: Context
) : NavHostController(
    context
), LifecycleOwner, DefaultLifecycleObserver {

    val menuState: MutableState<Boolean> = mutableStateOf(true)

    val menuItems: SnapshotStateList<MenuItem> = mutableStateListOf()

    val menuClickListeners: MutableList<MenuItemClickListener> = mutableListOf()

    override val lifecycle: Lifecycle by lazy { LifecycleRegistry(this) }

    init {
        lifecycle.addObserver(this)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        lifecycle.removeObserver(this)
        clearClickListeners()
        clearMenuItems()
    }

    fun addMenuItem(vararg item: MenuItem) {
        item.forEach { mi -> addUniqueMenuItem(mi) }
    }

    fun removeMenuItem(vararg item: MenuItem) {
        menuItems.removeAll(item.toSet())
    }

    fun clearMenuItems() {
        menuItems.clear()
    }

    fun addMenuClickListener(vararg listener: MenuItemClickListener) {
        menuClickListeners.addAll(listener)
    }

    fun removeMenuClickListener(vararg listener: MenuItemClickListener) {
        menuClickListeners.removeAll(listener.toSet())
    }

    fun clearClickListeners() {
        menuClickListeners.clear()
    }

    fun onMenuItemClick(item: MenuItem) {
        menuClickListeners.forEach { l ->
            l.onMenuItemClick(item)
        }
    }

    fun indexOfMenuItem(item: MenuItem): Int {
        return menuItems.indexOf(item)
    }

    fun addUniqueMenuItem(item: MenuItem) {
        menuItems.addUnique(item)
    }

    fun menuItem(id: Int): MenuItem {
        return menuItems[id]
    }

    @SuppressLint("ComposableNaming")
    @Composable
    fun forEachMenuItem(block: @Composable (index: Int, menuItem: MenuItem) -> Unit) {
        menuItems.forEachIndexed { idx, mi ->
            block.invoke(idx, mi)
        }
    }

}