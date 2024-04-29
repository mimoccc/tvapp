/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.other

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode

@Suppress("unused")
object Preferences {

    @SuppressLint("UnrememberedMutableState")
    @Suppress("UNCHECKED_CAST")
    @Composable
    fun rememberBooleanPref(
        name: String,
        defaultValue: Boolean = false,
        key: Any? = Unit,
        repoName: String = "settings",
    ): MutableState<Boolean> {
        val context = LocalContext.current
        val isEditMode = isEditMode()
        val pref = if (isEditMode) {
            mutableStateOf(false)
        } else {
            PreferenceProperty(context, repoName, name, defaultValue) as MutableState<Boolean>
        }
        if (pref is PreferenceProperty<*>) {
            DisposableEffect(key1 = key) {
                pref.registerForChanges()
                onDispose {
                    pref.unregister()
                }
            }
        }
        return remember(key) { pref }
    }

    fun <T> SharedPreferences.set(
        name: String,
        value: T
    ) {
        when (value) {
            is Boolean -> {
                edit().putBoolean(name, value).apply()
            }

            is Int -> {
                edit().putInt(name, value).apply()
            }

            is Float -> {
                edit().putFloat(name, value).apply()
            }

            is Long -> {
                edit().putLong(name, value).apply()
            }

            is String -> {
                edit().putString(name, value).apply()
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> SharedPreferences.get(
        name: String,
        defaultValue: T?
    ): T? {
        val ret: T? = all.toList().firstOrNull {
            it.first?.contentEquals(name) ?: false
        }?.second as? T
        return ret ?: defaultValue
    }

}
