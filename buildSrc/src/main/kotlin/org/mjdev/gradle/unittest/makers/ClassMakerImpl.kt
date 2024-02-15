/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.unittest.makers

import org.mjdev.gradle.extensions.file
import org.mjdev.gradle.unittest.Constants.Companion.DIRECTORY_SEPARATOR
import org.mjdev.gradle.unittest.Constants.Companion.JAVA_DIRECTORY
import org.mjdev.gradle.unittest.Constants.Companion.JAVA_FILE_EXTENSION
import org.mjdev.gradle.unittest.Constants.Companion.KOTLIN_FILE_EXTENSION
import org.mjdev.gradle.unittest.Constants.Companion.PACKAGE_SEPARATOR
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.net.URL
import java.net.URLClassLoader
import java.nio.file.Path


@Suppress("LocalVariableName")
internal class ClassMakerImpl(
    private var urls: Array<URL>
) : ClassMaker {
    companion object {
        private const val ANDROID_DIRECTORY = "ANDROID_SDK_DIRECTORY"
        private const val ANDROID_JAR = "android.jar"
        private const val FILE_URL_PREFIX = "file:"

        private val androidDirs = listOf(
            "/home/mimo/Android/Sdk"
        )
    }

    private var classloader: ClassLoader

    init {
        var SDKLocation = System.getenv(ANDROID_DIRECTORY)
        if (SDKLocation == null) {
            androidDirs.forEach { path ->
                if(File(path).exists() && (SDKLocation == null)) {
                    SDKLocation = path
                }
            }
        }
        println("> Using SDK : $SDKLocation")
        if (SDKLocation != null) {
            File(SDKLocation).file("platforms").listFiles()?.forEach { platformDir ->
                val url = URL(FILE_URL_PREFIX + platformDir + DIRECTORY_SEPARATOR + ANDROID_JAR)
                urls += url
            }
        }
        println(">Got platforms:")
        urls.forEach {
            println("> $it")
        }
        classloader = URLClassLoader(urls)
//        classloader = FileClassLoader()
    }

    override fun makeClass(path: Path): Class<*>? {
        val className = path.toString()
            .substringAfter(DIRECTORY_SEPARATOR + JAVA_DIRECTORY + DIRECTORY_SEPARATOR)
            .replace(DIRECTORY_SEPARATOR, PACKAGE_SEPARATOR)
            .removeSuffix(KOTLIN_FILE_EXTENSION)
            .removeSuffix(JAVA_FILE_EXTENSION)
        return runCatching {
            val x = classloader.loadClass(className)
            if (!x.isInterface) x else null
        }.onFailure { error ->
            println("> ${error::class.simpleName}: ${error.message}")
            println(error)
        }.getOrNull()
    }

}