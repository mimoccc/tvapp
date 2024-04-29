/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.plugin.config.base

import com.android.build.api.dsl.BuildType

typealias BuildTypeFnc = BuildType.() -> Unit

@Suppress("MemberVisibilityCanBePrivate")
abstract class BuildConfigs {

    val buildTypes = mutableMapOf<String, BuildTypeFnc>()

    fun create(name: String, action: BuildTypeFnc) {
        buildTypes[name] = action
    }

    fun default(action: BuildTypeFnc) = create("default", action)

    fun debug(action: BuildTypeFnc) = create("debug", action)

    fun release(action: BuildTypeFnc) = create("release", action)

    fun minified(action: BuildTypeFnc) = create("minified", action)

    fun test(action: BuildTypeFnc) = create("test", action)

    fun publish(action: BuildTypeFnc) = create("publish", action)

}