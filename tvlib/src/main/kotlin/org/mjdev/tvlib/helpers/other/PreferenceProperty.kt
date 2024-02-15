/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.other

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import org.mjdev.tvlib.helpers.other.Preferences.get
import org.mjdev.tvlib.helpers.other.Preferences.set

class PreferenceProperty<T>(
    context: Context,
    repoName: String = "settings",
    private val name: String,
    private val defaultValue: T? = null
) : MutableState<T?>, SharedPreferences.OnSharedPreferenceChangeListener {

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(repoName, Context.MODE_PRIVATE)
    }

    private val init: () -> T? = {
        prefs.get(name, defaultValue)
    }

    private var _value = mutableStateOf(init())

    override var value: T?
        get() =
            _value.value
        set(value) {
            prefs.set(name, value)
            _value.value = value
        }

    override fun component1(): T? =
        value

    override fun component2(): (T?) -> Unit = { v ->
        value = v
    }

    override fun onSharedPreferenceChanged(
        sharedPreferences: SharedPreferences?,
        key: String?
    ) {
        if (key != null && name.contentEquals(key)) {
            _value.value = value
        }
    }

    fun registerForChanges() {
        prefs.registerOnSharedPreferenceChangeListener(this@PreferenceProperty)
    }

    fun unregister() {
        prefs.unregisterOnSharedPreferenceChangeListener(this@PreferenceProperty)
    }

}
