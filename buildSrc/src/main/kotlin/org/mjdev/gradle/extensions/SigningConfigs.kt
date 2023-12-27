/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.gradle.extensions

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import java.io.FileInputStream
import java.util.*
import kotlin.String

fun BaseExtension.setSigningConfigs(
    project: Project
) = signingConfigs {
    create("signingConfigRelease") {
        val rootProject = project.rootProject
        val keystorePropertiesFile = rootProject.file("signing/release.signing.properties")
        if (!keystorePropertiesFile.exists()) {
            System.err.println("📜 Missing release.signing.properties file for release signing")
        } else {
            val keystoreProperties = Properties().apply {
                load(FileInputStream(keystorePropertiesFile))
            }
            try {
                storeFile = rootProject.file(keystoreProperties["storeFile"] as String)
                storePassword = keystoreProperties["storePassword"] as String
                keyAlias = keystoreProperties["keyAlias"] as String
                keyPassword = keystoreProperties["keyPassword"] as String
            } catch (e: Exception) {
                System.err.println("📜 release.signing.properties file is malformed")
            }
        }
    }

    getByName("debug") {
        storeFile = project.rootProject.file("signing/debug.keystore")
        keyAlias = "androiddebugkey"
        keyPassword = "android"
        storePassword = "android"
    }
}
