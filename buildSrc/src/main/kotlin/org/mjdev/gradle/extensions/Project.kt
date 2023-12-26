/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.gradle.extensions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.api.AndroidBasePlugin
import org.gradle.api.Action
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.kotlin.dsl.extra
import java.io.File
import java.io.FileInputStream
import java.util.Properties
import java.util.regex.Pattern
import kotlin.String

private const val VERSION_TAG_MAJOR = "majorVersion"
private const val VERSION_TAG_MINOR = "minorVersion"
private const val VERSION_TAG_PATCH = "patchVersion"

private const val TASK_DOKKA = "dokkaGfm"

fun Project.android(): BaseExtension {
    val android = project.extensions.findByType(BaseExtension::class.java)
    if (android != null) {
        return android
    } else {
        throw GradleException("Project $name is not an Android project")
    }
}

val Project.versionCode
    get(): Int {
        val major = runCatching {
            rootProject.extra.get(VERSION_TAG_MAJOR)?.toString()?.toInt()
        }.getOrNull() ?: 1
        val minor = runCatching {
            rootProject.extra.get(VERSION_TAG_MINOR)?.toString()?.toInt()
        }.getOrNull() ?: 0
        val patch = runCatching {
            rootProject.extra.get(VERSION_TAG_PATCH)?.toString()?.toInt()
        }.getOrNull() ?: 0
        return major * 10000 + minor * 100 + patch
    }

val Project.versionName: String
    get() {
        val major = runCatching {
            rootProject.extra.get(VERSION_TAG_MAJOR)?.toString()?.toInt()
        }.getOrNull() ?: 1
        val minor = runCatching {
            rootProject.extra.get(VERSION_TAG_MINOR)?.toString()?.toInt()
        }.getOrNull() ?: 0
        val patch = runCatching {
            rootProject.extra.get(VERSION_TAG_PATCH)?.toString()?.toInt()
        }.getOrNull() ?: 0
        return "${major}.${minor}.${patch}"
    }

private val Project.taskRequestsStr
    get() = gradle.startParameter.taskRequests.toString()

val Project.currentFlavor: String
    get() {
        val pattern: Pattern = if (taskRequestsStr.contains("assemble")) {
            Pattern.compile("assemble(\\w+)(Release|Debug)")
        } else {
            Pattern.compile("bundle(\\w+)(Release|Debug)")
        }
        val matcher = pattern.matcher(taskRequestsStr)
        val flavor = if (matcher.find()) {
            matcher.group(1).lowercase()
        } else {
            ""
        }
        return flavor
    }

val Project.dokkaGfm get() = tasks.findByName(TASK_DOKKA)

inline fun <reified T : Task> Project.registerTask(
    name: String,
    block: Action<T>
) = project.tasks.register(name, T::class.java).configure(block)

val Project.android
    get() = extensions.getByType(ApplicationExtension::class.java)

val Project.androidComponents
    get() = extensions.getByType(AndroidComponentsExtension::class.java)

val Project.containsAndroidPlugin: Boolean
    get() = project.plugins.toList().any { plugin -> plugin is AndroidBasePlugin }

fun Project.loadKeyStoreProperties(
    signConfigFilePath: String,
    block: (keyAlias: String, keyPassword: String, storeFile: File, storePassword: String) -> Unit
) {
    val keystorePropertiesFile = project.rootProject.file(signConfigFilePath)
    val keystoreProperties = Properties()
    if (keystorePropertiesFile.exists()) {
        keystoreProperties.load(FileInputStream(keystorePropertiesFile))
    } else {
        keystoreProperties["keyAlias"] = System.getenv("KEYSTORE_KEY_ALIAS").orEmpty()
        keystoreProperties["keyPassword"] = System.getenv("KEYSTORE_KEY_PASSWORD").orEmpty()
        keystoreProperties["storePassword"] =
            System.getenv("KEYSTORE_STORE_PASSWORD").orEmpty()
        keystoreProperties["storeFile"] = System.getenv("KEYSTORE_FILE").orEmpty()
    }
    block.invoke(
        keystoreProperties["keyAlias"] as String? ?: "",
        keystoreProperties["keyPassword"] as? String? ?: "",
        File(projectDir, keystoreProperties["storeFile"] as? String? ?: ""),
        keystoreProperties["storePassword"] as? String? ?: ""
    )
}

fun Project.propOrDef(propertyName: String, defaultValue: Any): Any = project
    .properties[propertyName] ?: (System.getenv(propertyName) ?: defaultValue)

fun Project.getFile(vararg fileNames: String): File? {
    for (fileName in fileNames) {
        val file = rootProject.file(fileName)
        if (file.exists()) {
            return file
        }
    }
    return null
}

fun Project.propertyOrEmpty(name: String): String = findProperty(name) as String? ?: ""
