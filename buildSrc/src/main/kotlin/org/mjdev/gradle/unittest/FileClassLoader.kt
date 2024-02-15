/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.unittest

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException

class FileClassLoader : ClassLoader() {
    @Throws(ClassNotFoundException::class)
    public override fun findClass(name: String): Class<*> {
        val b = loadClassFromFile(name)
        return defineClass(name, b, 0, b.size)
    }

    private fun loadClassFromFile(fileName: String): ByteArray {
        val inputStream = javaClass.classLoader.getResourceAsStream(
            fileName.replace('.', File.separatorChar) + ".class"
        )
        val buffer: ByteArray
        val byteStream = ByteArrayOutputStream()
        var nextValue:Int
        try {
            if (inputStream != null) {
                while (inputStream.read().also { nextValue = it } != -1) {
                    byteStream.write(nextValue)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        buffer = byteStream.toByteArray()
        return buffer
    }
}