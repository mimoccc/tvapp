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
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
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
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

private const val DEFAULT_BUFFER_SIZE: Int = 8 * 1024

fun File.bufferedOutputStream(size: Int = kotlin.io.DEFAULT_BUFFER_SIZE) =
    BufferedOutputStream(this.outputStream(), size)

fun File.zipOutputStream(size: Int = kotlin.io.DEFAULT_BUFFER_SIZE) =
    ZipOutputStream(this.bufferedOutputStream(size))

fun File.bufferedInputStream(size: Int = kotlin.io.DEFAULT_BUFFER_SIZE) =
    BufferedInputStream(this.inputStream(), size)

fun zipArchive(
    files: List<File>,
    destination: File,
    excludes: List<String> = emptyList() // todo
) {
    destination.zipOutputStream().use { zipStream ->
        zipArchive(files, zipStream, "", excludes)
    }
}

private fun zipArchive(
    files: List<File>,
    zipStream: ZipOutputStream,
    dirName:String="",
    excludes: List<String> = emptyList() // todo
) {
    files.forEach { file ->
        if (file.exists()) {
            val fileName = if (file.isDirectory) {
                "${if (dirName.isNotEmpty()) dirName else ""}${file.name}/"
            } else {
                "${if (dirName.isNotEmpty()) dirName else ""}${file.name}"
            }
            zipStream.putNextEntry(ZipEntry(fileName))
            if (file.isFile) {
                file.bufferedInputStream().use { bis ->
                    bis.copyTo(zipStream)
                }
            } else {
                file.listFiles()?.let {
                    zipArchive(it.toList(), zipStream, fileName, excludes)
                }
            }
        }
    }
}

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
