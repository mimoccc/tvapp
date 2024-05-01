/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.extensions

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project
import java.io.FileInputStream
import java.util.*
import org.mjdev.gradle.custom.SigningsScope

fun ApplicationExtension.setSigningConfigs(
    project: Project,
    scope: SigningsScope = SigningsScope(),
    config: SigningsScope.() -> Unit = {}
) = signingConfigs {
    config.invoke(scope)
    create("release") {
        val keystorePropertiesFile = project.rootDir.resolve(scope.releaseKeyFile)
        if (keystorePropertiesFile.exists()) {
            try {
                val properties = Properties().apply {
                    load(FileInputStream(keystorePropertiesFile))
                }
                storeFile = project.rootDir.resolve(properties["storeFile"].toString())
                storePassword = properties["storePassword"].toString()
                keyAlias = properties["keyAlias"].toString()
                keyPassword = properties["keyPassword"].toString()
            } catch (e: Exception) {
                System.err.println("> File $keystorePropertiesFile is malformed or error.")
                System.err.println("> ${e.message}.")
            }
        } else {
            System.err.println("> Missing $keystorePropertiesFile file for release signing.")
        }
    }
    getByName("debug") {
        val keystorePropertiesFile = project.rootDir.resolve(scope.debugKeyFile)
        if (keystorePropertiesFile.exists()) {
            try {
                val properties = Properties().apply {
                    load(FileInputStream(keystorePropertiesFile))
                }
                storeFile = project.rootDir.resolve(properties["storeFile"].toString())
                storePassword = properties["storePassword"].toString()
                keyAlias = properties["keyAlias"].toString()
                keyPassword = properties["keyPassword"].toString()
            } catch (e: Exception) {
                System.err.println("> File $keystorePropertiesFile is malformed or error.")
                System.err.println("> ${e.message}.")
            }
        } else {
            System.err.println("> Missing $keystorePropertiesFile file for release signing.")
        }
    }
}
