/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.plugin.config

open class LibConfig {
    open var autoCorrectCode = true
    open var ignoreCodeFailures = true
    open var createDocumentation = false
    open var reportUndocumentedFiles = false
    open var failOnDocumentationWarning = false

    open var codeReportsDir = "reports/lib"
    open var documentationDir = "documentation/lib"
    open var detectConfigFile = "config/detekt.yml"
}