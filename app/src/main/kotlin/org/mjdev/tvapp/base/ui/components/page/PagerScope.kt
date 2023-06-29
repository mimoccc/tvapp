/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.ui.components.page

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.mjdev.tvapp.base.extensions.ListExt.addUnique
import org.mjdev.tvapp.base.ui.components.navigation.MenuItem
import org.mjdev.tvapp.base.ui.components.navigation.NavHostControllerEx

class PagerScope(
    val navController: NavHostControllerEx,
    pages: PagerScope.() -> Unit = {}
) : ArrayList<Page>() {

    init {
        pages.invoke(this)
    }

    fun page(page: Page) {
        page.navController = navController
        addUnique(page)
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

}

@Composable
fun rememberPagerScope(
    navController: NavHostControllerEx,
    pages: PagerScope.() -> Unit = {}
) = remember {
    PagerScope(
        navController,
        pages
    )
}