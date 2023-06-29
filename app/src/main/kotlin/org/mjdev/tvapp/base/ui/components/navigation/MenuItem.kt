/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.ui.components.page.Page

@Suppress("unused")
data class MenuItem(

    var menuText: Any? = null,
    var menuIcon: Any? = null,

    var menuGravity: Gravity = Gravity.Center,

    var menuAction: (() -> Unit)? = null,
    var menuRoute: String? = null,
    var menuPage: Page? = null,

    var enabled: Boolean = true,

    ) {

    val isPage: Boolean get() = (menuPage != null)

    val isRoute: Boolean get() = (menuRoute != null)

    val isAction: Boolean get() = (menuAction != null)

    val isEnabled: Boolean get() = (((menuText != null) || (menuIcon != null)) && enabled)

    fun gravity(gravity: Gravity): MenuItem = apply {
        menuGravity = gravity
    }

    fun icon(icon: Any?): MenuItem = apply {
        menuIcon = icon
    }

    fun text(text: Any?): MenuItem = apply {
        menuText = text
    }

    fun action(action: () -> Unit): MenuItem = apply {
        menuAction = action
    }

    enum class Gravity {
        Top,
        Bottom,
        Center
    }

    override fun toString(): String = "Menu [ $menuText ] action:$isAction, page:$isPage, route:$isRoute"

    companion object {

        val MENU_ITEM_SEARCH = MenuItem(
            menuText = R.string.menu_title_search,
            menuIcon = Icons.Default.Search,
        )

        val MENU_ITEM_SETTINGS = MenuItem(
            menuText = R.string.menu_title_settings,
            menuIcon = Icons.Default.Settings,
        )

        val MENU_ITEM_EXIT = MenuItem(
            menuText = R.string.menu_title_exit,
            menuIcon = Icons.Default.ExitToApp,
        )

    }

}