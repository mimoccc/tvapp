/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.tasks

import org.gradle.internal.impldep.org.eclipse.jgit.api.Git
import org.gradle.internal.impldep.org.eclipse.jgit.internal.storage.file.FileRepository
import org.mjdev.gradle.base.BaseTask
import org.mjdev.gradle.extensions.file
import org.mjdev.gradle.extensions.projectVersion
import org.mjdev.gradle.extensions.toDateString
import org.mjdev.gradle.extensions.writeText
import java.io.File
import java.util.Date
import java.util.Locale
import org.mjdev.gradle.extensions.println

open class ReleaseNotesCreateTask : BaseTask() {

    private val branchName = "main"
    private val repo by lazy { FileRepository(project.rootProject.file(".git")) }
    private val git: Git by lazy { Git(repo) }
    private val branches by lazy { git.branchList().call() }

    private val fileName: String
        get() = "release-notes-$projectVersion.md"

    private val docDir: File
        get() = project.rootProject.file("documentation")

    private val outputFiles = listOf(
        docDir.file(fileName)
    )

    init {
        group = "mjdev"
        description = "This task create release-notes files, for the project."
        outputs.upToDateWhen { false }
    }

    override fun onClean() {
        outputFiles.forEach { file ->
            if (file.isDirectory) {
                file.deleteRecursively()
            } else {
                file.delete()
            }
        }
    }

    override fun onAssemble() {
        StringBuilder().apply {
            @Suppress("DEPRECATION")
            append("## Release notes $projectVersion - ${Date().toLocaleString()}\n")
            branches.forEach { ref ->
                if (ref.name.endsWith(branchName)) {
                    val commits = git.log().all().call().sortedByDescending {
                        it.authorIdent.`when`
                    }
                    var lastDate: String? = null
                    commits.forEach { commit ->
                        val author = commit.authorIdent
                            .name
                            .lowercase(Locale.getDefault())
                        val date = commit.authorIdent.`when`.toDateString()
                        val message = commit.fullMessage
                            .replace("\n", " ")
                            .lowercase(Locale.getDefault())
                        if (lastDate == null || !lastDate.contentEquals(date)) {
                            lastDate = date
                            append("\n")
                            append("* $lastDate\n")
                            append("\n")
                        }
                        append("* [$author] : $message\n")
                    }
                }
            }
        }.toString().let { text ->
            outputFiles.forEach { of ->
                println("Creating release file: ${of.absolutePath}")
                of.writeText(text)
            }
        }
    }

}
