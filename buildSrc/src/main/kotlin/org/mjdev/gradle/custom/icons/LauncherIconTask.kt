/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.custom.icons

import org.gradle.api.tasks.OutputDirectory
import org.mjdev.gradle.base.BaseTask
import org.mjdev.gradle.extensions.androidManifestFiles
import org.mjdev.gradle.extensions.fileTree
import org.mjdev.gradle.extensions.resOutputDirectory
import org.mjdev.gradle.custom.icons.filter.LauncherIconFilter
import org.mjdev.gradle.custom.icons.utils.Resources
import java.io.File

@Suppress("MemberVisibilityCanBePrivate")
open class LauncherIconTask : BaseTask() {

    val outputDir
        @OutputDirectory
        get() = resOutputDirectory

    private var iconNames: MutableSet<String> = HashSet()
    private var foregroundIconNames: MutableSet<String> = HashSet()

    private var filters: List<LauncherIconFilter> = ArrayList()

    private val launcherIconNames: Set<String>
        get() = HashSet<String>().apply {
            androidManifestFiles.forEach { manifestFile ->
                addAll(Resources.getLauncherIcons(manifestFile))
            }
        }

    init {
        group = "mjdev"
        description = "Generate icon due application variant"
    }

    fun doTask() {
        if (filters.isEmpty()) return
        val names = HashSet<String>().apply {
            iconNames.forEach { add(it) }
            addAll(launcherIconNames)
        }
        val foregroundNames = HashSet<String>().apply {
            foregroundIconNames.forEach { add(it) }
        }
        variant.sourceSets.forEach { sourceProvider ->
            sourceProvider.resDirectories.forEach { resDir ->
                if (resDir.absolutePath == outputDir.absolutePath) return
                names.forEach { name ->
                    project.fileTree(
                        resDir,
                        Resources.resourceFilePattern(name),
                        "**/*.xml"
                    ).forEach { inputFile ->
                        val basename: String? = inputFile.name
                        val resType: String? = inputFile.parentFile.name
                        val outputFile = File(outputDir, "${resType}/${basename}")
                        outputFile.parentFile.mkdirs()
                        val easyLauncher = LauncherIcon(inputFile, outputFile)
                        easyLauncher.process(filters.stream())
                        easyLauncher.save()
                    }
                }
                foregroundNames.forEach { name ->
                    project.fileTree(
                        resDir,
                        Resources.resourceFilePattern(name),
                        "**/*.xml"
                    ).forEach { inputFile ->
                        val basename = inputFile.name
                        val resType = inputFile.parentFile.name
                        val outputFile = File(outputDir, "${resType}/${basename}")
                        outputFile.parentFile.mkdirs()
                        val largeRibbonFilters = filters.map { filter ->
                            filter.setAdaptiveLauncherMode(true)
                            filter
                        }
                        val easyLauncher = LauncherIcon(inputFile, outputFile)
                        easyLauncher.process(largeRibbonFilters.stream())
                        easyLauncher.save()
                    }
                }
            }
        }
    }

    override fun onClean() {
        TODO("Not yet implemented")
    }

    override fun onAssemble() {
        TODO("Not yet implemented")
    }

}