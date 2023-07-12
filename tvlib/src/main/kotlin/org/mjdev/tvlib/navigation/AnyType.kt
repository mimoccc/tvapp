/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.navigation

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.NavType
import java.lang.RuntimeException

@Suppress("DEPRECATION")
object AnyType : NavType<Any?>(isNullableAllowed = true) {

    override fun get(bundle: Bundle, key: String): Any? {
        return bundle.get(key)
    }

    override fun parseValue(value: String): Any? {
        throw(RuntimeException("Not yet implemented."))
    }

    override fun put(bundle: Bundle, key: String, value: Any?) {
        bundle.putAll(bundleOf(key to value))
    }

}