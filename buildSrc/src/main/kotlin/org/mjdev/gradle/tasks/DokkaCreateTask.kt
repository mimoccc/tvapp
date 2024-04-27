/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */
package org.mjdev.gradle.tasks
//
//import org.jetbrains.dokka.gradle.DokkaTask
import org.mjdev.gradle.extensions.file
import org.mjdev.gradle.extensions.projectName
import java.io.File
//
//open class DokkaCreateTask : DokkaTask() {
//
//    private val documentationDir: File
//        get() = project.rootProject.file("documentation")
//
//    private val docProjectDir: File
//        get() = documentationDir
//            .file(projectName)
//            .file("documentation")
//
//    init {
//        group = "mjdev"
//        description = "Documentation create task."
//
//        outputDirectory = docProjectDir.absolutePath
//        sourceDirs = listOf(File(project.projectDir.absolutePath))
//        outputFormat = "gfm"
//        includeNonPublic = true
//        reportUndocumented = true
//        sourceRoot {
//            path = File(project.rootDir, "src/main/kotlin").absolutePath
//        }
//        skipEmptyPackages = false
//        collectInheritedExtensionsFromLibraries = false
//        outputFormat = "gfm"
//
//        outputs.upToDateWhen { false }
//    }
//
//}
