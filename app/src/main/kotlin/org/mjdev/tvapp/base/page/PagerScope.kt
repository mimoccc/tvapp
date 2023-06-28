/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.page

import org.mjdev.tvapp.base.extensions.ListExt.addUnique
import org.mjdev.tvapp.base.navigation.MenuItem
import org.mjdev.tvapp.base.navigation.NavHostControllerEx
import org.mjdev.tvapp.base.state.ScreenState

class PagerScope(
    val navController: NavHostControllerEx? = null,
    private val screenState: ScreenState? = null,
    private val menuItems: MutableList<MenuItem> = mutableListOf(),
) : ArrayList<Page>() {

    fun page(page: Page) {
        page.navController = navController
        page.screenState = screenState
        addUnique(page)
        page.let { p ->
            MenuItem(p.title, p.icon)
        }.also { menuItem ->
            menuItems.addUnique(menuItem)
        }
    }



}