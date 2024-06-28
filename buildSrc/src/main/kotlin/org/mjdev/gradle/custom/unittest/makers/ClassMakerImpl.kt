/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("LocalVariableName")

package org.mjdev.gradle.custom.unittest.makers

import org.mjdev.gradle.extensions.file
import java.net.URL

import org.mjdev.gradle.custom.unittest.Constants.Companion.DIRECTORY_SEPARATOR
import org.mjdev.gradle.custom.unittest.Constants.Companion.JAVA_DIRECTORY
import org.mjdev.gradle.custom.unittest.Constants.Companion.JAVA_FILE_EXTENSION
import org.mjdev.gradle.custom.unittest.Constants.Companion.KOTLIN_FILE_EXTENSION
import org.mjdev.gradle.custom.unittest.Constants.Companion.PACKAGE_SEPARATOR
import java.io.File
import java.net.URLClassLoader
import java.nio.file.Path

@Suppress("PrivatePropertyName")
internal class ClassMakerImpl(
    private var urls: List<URL>
) : ClassMaker {
    private val ANDROID_DIRECTORY = "ANDROID_SDK_DIRECTORY"
    private val ANDROID_JAR = "android.jar"
    private val FILE_URL_PREFIX = "file:"

    // todo window & mac
    private val ANDROID_DIRS = listOf(
        "/home/mimo/Android/Sdk"
    )

    private var classloader: ClassLoader

    init {
        var SDKLocation = System.getenv(ANDROID_DIRECTORY)
        if (SDKLocation == null) {
            ANDROID_DIRS.forEach { path ->
                if (File(path).exists() && (SDKLocation == null)) {
                    SDKLocation = path
                }
            }
        }
        if (SDKLocation != null) {
            File(SDKLocation).file("platforms").listFiles()?.forEach { platformDir ->
                val url = URL(FILE_URL_PREFIX + platformDir + DIRECTORY_SEPARATOR + ANDROID_JAR)
                urls += url
            }
        }
        if (SDKLocation != null) {
            val ANDROID = URL(FILE_URL_PREFIX + SDKLocation + DIRECTORY_SEPARATOR + ANDROID_JAR)
            urls += ANDROID
        }
        classloader = URLClassLoader(urls.toTypedArray(), Thread.currentThread().contextClassLoader)
    }

    override fun makeClass(path: Path): Class<*>? = runCatching {
        val className = path.toString()
            .substringAfter(DIRECTORY_SEPARATOR + JAVA_DIRECTORY + DIRECTORY_SEPARATOR)
            .replace(DIRECTORY_SEPARATOR, PACKAGE_SEPARATOR)
            .removeSuffix(KOTLIN_FILE_EXTENSION)
            .removeSuffix(JAVA_FILE_EXTENSION)
        val x = classloader.loadClass(className)
        if (!x.isInterface) x else null
    }.onFailure { error ->
        println("> Error: $error")
    }.getOrNull()
}