/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.extensions

import org.gradle.api.Plugin
import org.gradle.api.plugins.PluginManager

inline fun <reified T : Plugin<*>> PluginManager.apply() = apply(T::class.java)
