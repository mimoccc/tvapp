/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import org.mjdev.tvapp.base.extensions.ListExt.addUnique
import org.mjdev.tvapp.base.navigation.MenuItem
import org.mjdev.tvapp.base.navigation.NavHostControllerEx

@Suppress("unused")
class PagerScope(
    val navController: NavHostControllerEx,
    pages: PagerScope.() -> Unit = {},
) : ArrayList<Page>() {

    val currentPage: MutableState<Int> = mutableIntStateOf(0)

    init {
        pages.invoke(this)
    }

    fun menuItem(vararg item: MenuItem) {
        navController.addMenuItem(*item)
    }

    fun page(page: Page, isStartPage: Boolean = false) {
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
                menuGravity = p.menuGravity,
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
    pages: PagerScope.() -> Unit = {}
) = remember {
    PagerScope(
        navController,
        pages,
    )
}