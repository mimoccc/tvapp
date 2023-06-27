package org.mjdev.tvapp.base.page

import androidx.navigation.NavHostController
import org.mjdev.tvapp.base.navigation.MenuItem
import org.mjdev.tvapp.base.state.ScreenState

class PagerScope(
    val navController: NavHostController? = null,
    val screenState: ScreenState? = null,
    val menuItems: MutableList<MenuItem> = mutableListOf(),
) : ArrayList<Page>() {

    fun page(page: Page) {
        page.navController = navController
        page.screenState = screenState
        page.menuItem?.also { menuItem ->
            menuItems.firstOrNull { mi -> mi.menuText == page.title }.also { duplicate ->
                if (duplicate != null) menuItems.remove(duplicate)
            }
            menuItems.add(menuItem)
        }
        addUnique(page)
    }

    private fun addUnique(page: Page) {
        firstOrNull { p -> p.title == page.title }.also { duplicate ->
            if (duplicate != null) remove(duplicate)
        }
        add(page)
    }

}