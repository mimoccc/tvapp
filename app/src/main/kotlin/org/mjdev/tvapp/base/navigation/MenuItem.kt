/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.navigation

data class MenuItem(

    val menuText: Any? = null,
    val menuIcon: Any? = null,

    var route: String? = null,

) {

    override fun toString(): String = menuText.toString()

}