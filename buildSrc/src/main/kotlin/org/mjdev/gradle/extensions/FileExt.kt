/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.gradle.extensions

import org.gradle.internal.impldep.org.apache.commons.io.FileUtils
import java.io.File
import java.nio.file.Files
import java.nio.file.LinkOption
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.BasicFileAttributeView
import java.nio.file.attribute.BasicFileAttributes
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun File.file(
    name: String
): File = File(this, name)

fun String.asFile() = File(this)

fun File.writeText(
    text: String
) {
    parentFile.mkdirs()
    writeText(text, charset("UTF-8"))
}

fun File?.getCreateTime(): LocalDateTime? {
    return this?.let {
        if (exists()) {
            try {
                val path: Path = Paths.get(this.path)
                val basicfile: BasicFileAttributeView = Files.getFileAttributeView(
                    path,
                    BasicFileAttributeView::class.java,
                    LinkOption.NOFOLLOW_LINKS
                )
                val attr: BasicFileAttributes = basicfile.readAttributes()
                val date: Long = attr.creationTime().toMillis()
                val instant: Instant = Instant.ofEpochMilli(date)
                LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
            } catch (e: Exception) {
                null
            }
        } else
            null
    } ?: LocalDateTime.MIN
}

fun File.moveTo(newFile: File) {
    if (exists()) {
        if (newFile.exists()) newFile.delete()
        this.copyTo(newFile)
        this.delete()
    }
}

val File.dirSize: Int
    get() = if (!isDirectory) 0 else listFiles()?.size ?: 0

val File.dirEmpty: Boolean get() = dirSize == 0

fun File.isNewer(compareTo: File): Boolean {
    return if (exists() && compareTo.exists())
        FileUtils.isFileNewer(this, compareTo)
    else false
}

fun File.copyDirectory(toDir: File?) {
    if (exists()) {
        toDir?.let {
            FileUtils.copyDirectory(this, it)
        }
    }
}

fun File.readTextLines(maxLength: Long): String {
    if (!exists()) error("File must exist: $this")
    if (length() == 0L) error("File must not be empty: $this")
    var complete = false
    val text = StringBuilder()
    forEachLine { line ->
        if (complete) return@forEachLine
        if (text.length + line.length < maxLength) {
            text.append(line.trim()).append("\n")
        } else {
            complete = true
        }
    }
    return text.toString()
}

fun assertPathIsDirectory(path: File) {
    require(path.isDirectory) { String.format("'%s' is no directory", path) }
}

fun File.allFiles(ext: String): List<File> = mutableListOf<File>().also { list ->
    listFiles()?.forEach { f ->
        when {
            f.extension == ext -> list.add(f)
            f.isDirectory -> list.addAll(f.allFiles(ext))
        }
    }
}

val File.asTestFile
    get() = File(nameWithoutExtension + "Test" + "." + extension)
