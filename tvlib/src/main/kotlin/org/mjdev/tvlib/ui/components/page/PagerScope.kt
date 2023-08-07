/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import org.mjdev.tvlib.extensions.ListExt.addUnique
import org.mjdev.tvlib.navigation.MenuItem
import org.mjdev.tvlib.navigation.NavHostControllerEx

@Suppress("unused", "PropertyName")
class PagerScope(
    val navController: NavHostControllerEx,
    startIndex:Int = 0,
    pages: PagerScope.() -> Unit = {},
) : ArrayList<Page>() {

    val Bottom = MenuItem.Gravity.Bottom
    val Center = MenuItem.Gravity.Center
    val Top = MenuItem.Gravity.Top

    val currentPage: MutableState<Int> = mutableIntStateOf(startIndex)

    init {
        pages.invoke(this)
    }

    fun menuItem(vararg item: MenuItem) {
        navController.addMenuItem(*item)
    }

    fun page(
        page: Page,
        menuGravity: MenuItem.Gravity = Center,
        isStartPage: Boolean = false
    ) {
        page.navController = navController
        addUnique(page)
        if (isStartPage) {
            currentPage.value = size - 1
        }
        page.let { p ->
            MenuItem(
                menuText = p.title,
                menuIcon = p.icon,
                menuPage = p,
                menuGravity = menuGravity,
            )
        }.also { menuItem ->
            navController.addUniqueMenuItem(menuItem)
        }
    }

    fun indexOfPage(page: Page?): Int {
        return if (page == null) return -1
        else {
            indexOfFirst { p ->
                p.toString() == page.toString()
            }
        }
    }

    fun scrollToPage(index: Int) {
        if (size > index && size > -1) {
            currentPage.value = index
        }
    }

}

@Composable
fun rememberPagerScope(
    navController: NavHostControllerEx,
    startIndex:Int = 0,
    pages: PagerScope.() -> Unit = {}
) = remember {
    PagerScope(
        navController,
        startIndex,
        pages,
    )
}