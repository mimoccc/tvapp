/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.gradle.extensions

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project
import java.io.FileInputStream
import java.util.*
import kotlin.String

class SigningsScope(
    var debugKeyFile: String = "",
    var releaseKeyFile: String = ""
)

fun ApplicationExtension.setSigningConfigs(
    project: Project,
    scope: SigningsScope = SigningsScope(),
    config: SigningsScope.() -> Unit = {}
) = signingConfigs {
    config.invoke(scope)
    create("release") {
        val rootProject = project.rootProject
        val keystorePropertiesFile = rootProject.file(scope.releaseKeyFile)
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
        val rootProject = project.rootProject
        val keystorePropertiesFile = rootProject.file(scope.debugKeyFile)
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
}
