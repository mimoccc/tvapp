/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.plugin.config

open class LibConfig {
    open var autoCorrectCode = false
    open var createDocumentation = false

    open var documentationDir = "documentation/lib"
    open var detectConfigFile = "config/detekt.ym"
}