/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.base.navigation

/**
 * Menu item helper class.
 *
 * Normally you do not need to use this class, it is used to autogenerate menu items in
 * navigation drawer of an application.
 *
 * @constructor Create [MenuItem]
 * @property menuText
 * @property menuIcon
 * @property route
 */
data class MenuItem(

    val menuText: Any? = null,
    val menuIcon: Any? = null,
    var route: String? = null,
    var pagerIndex: Int? = null

)