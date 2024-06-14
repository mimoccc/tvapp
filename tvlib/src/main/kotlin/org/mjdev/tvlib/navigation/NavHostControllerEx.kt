/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.navigation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.os.bundleOf
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.navigation.NavHostController
import androidx.tv.material3.DrawerState
import androidx.tv.material3.DrawerValue
import org.mjdev.tvlib.extensions.ListExt.addUnique
import java.io.Serializable

// todo apply lifecycle
@Suppress("unused", "MemberVisibilityCanBePrivate")
class NavHostControllerEx(
    context: Context
) : NavHostController(
    context
), LifecycleOwner, DefaultLifecycleObserver {

    val settingsDrawerState: DrawerState = DrawerState(DrawerValue.Closed)

    val menuDrawerState: DrawerState = DrawerState(DrawerValue.Closed)

    val menuState: MutableState<Boolean> = mutableStateOf(true)

    val menuItems: SnapshotStateList<MenuItem> = mutableStateListOf()

    val menuClickListeners: MutableList<MenuItemClickListener> = mutableListOf()

    override val lifecycle: Lifecycle by lazy { LifecycleRegistry(this) }

    val backgroundState: MutableState<Any?> = mutableStateOf(null)

    val selectedMenuItem: MutableIntState = mutableIntStateOf(-1)

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

    fun openSettings() {
        settingsDrawerState.setValue(DrawerValue.Open)
    }

    fun closeSettings() {
        settingsDrawerState.setValue(DrawerValue.Closed)
    }

    fun openMenu() {
        menuDrawerState.setValue(DrawerValue.Open)
    }

    fun closeMenu() {
        menuDrawerState.setValue(DrawerValue.Closed)
    }

    inline fun <reified T : Activity> startActivity(
        vararg params: Pair<String, Serializable?>
    ) {
        Intent(context, T::class.java).apply {
            putExtras(bundleOf(*params))
        }.also { i ->
            context.startActivity(i)
        }
    }

    fun setBackground(color: Color) {
        setBackground(ColorDrawable(color.toArgb()))
    }

    fun setBackground(drawable: Drawable) {
        backgroundState.value = drawable
    }

    fun setBackground(imageVector: ImageVector) {
        backgroundState.value = imageVector
    }

}
