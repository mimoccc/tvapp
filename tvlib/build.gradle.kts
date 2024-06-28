/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

import org.mjdev.gradle.extensions.isAndroidStudio

plugins {
    LibPlugin
}

libConfig {
    namespace = "org.mjdev.tvlib"
    description = "Smart TV android app lib for android applications running on any android device"

    autoCorrectCode = true
    ignoreCodeFailures = true

    createDocumentation = !isAndroidStudio
    reportUndocumentedFiles = !isAndroidStudio
    failOnDocumentationWarning = false

    minify = false
}