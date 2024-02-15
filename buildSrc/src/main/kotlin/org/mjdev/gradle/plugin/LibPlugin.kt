/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("UsePropertyAccessSyntax")

package org.mjdev.gradle.plugin

import com.android.build.gradle.LibraryExtension
import org.mjdev.gradle.base.PluginBase

@Suppress("unused", "MemberVisibilityCanBePrivate")
class LibPlugin : PluginBase<LibPluginParams, LibraryExtension>(LibPluginParams::class.java, {
    test {
//        println("Project variant: ${project.currentVariant}")
    }
    with(params) {
    }
})
