/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.reporter

import org.mjdev.gradle.versions.result.Dependency
import org.mjdev.gradle.versions.result.Result
import org.mjdev.gradle.versions.base.GradleReleaseChannel.CURRENT
import org.mjdev.gradle.versions.base.GradleReleaseChannel.NIGHTLY
import org.mjdev.gradle.versions.base.GradleReleaseChannel.RELEASE_CANDIDATE
import org.gradle.api.Project
import java.io.OutputStream

class PlainTextReporter(
    override val project: Project,
    override val revision: String,
    override val gradleReleaseChannel: String,
) : AbstractReporter(project, revision, gradleReleaseChannel) {
    override fun write(
        printStream: OutputStream,
        result: Result
    ) = with(printStream) {
        writeHeader(printStream)
        if (result.count == 0) {
            println()
            println("No dependencies found.")
        } else {
            writeUpToDate(printStream, result)
            writeExceedLatestFound(printStream, result)
            writeUpgrades(printStream, result)
            writeUndeclared(printStream, result)
            writeUnresolved(printStream, result)
        }
        writeGradleUpdates(printStream, result)
    }

    override fun getFileExtension(): String  = "txt"

    private fun writeHeader(
        printStream: OutputStream
    ) = with(printStream) {
        println()
        println("------------------------------------------------------------")
        println("${project.path} Project Dependency Updates (report to plain text file)")
        println("------------------------------------------------------------")
    }

    private fun writeUpToDate(
        printStream: OutputStream,
        result: Result
    ) = with(printStream) {
        val upToDateVersions = result.current.dependencies
        if (upToDateVersions.isNotEmpty()) {
            println()
            println("The following dependencies are using the latest $revision version:")
            for (dependency in upToDateVersions) {
                println(" - ${label(dependency)}:${dependency.version}")
                dependency.userReason?.let {
                    println("     $it")
                }
            }
        }
    }

    private fun writeExceedLatestFound(
        printStream: OutputStream,
        result: Result
    ) = with(printStream) {
        val downgradeVersions = result.exceeded.dependencies
        if (downgradeVersions.isNotEmpty()) {
            println()
            println(
                "The following dependencies exceed the version found at the $revision revision level:"
            )
            for (dependency in downgradeVersions) {
                val currentVersion = dependency.version
                println(" - ${label(dependency)} [$currentVersion <- ${dependency.latest}]")
                dependency.userReason?.let {
                    println("     $it")
                }
                dependency.projectUrl?.let {
                    println("     $it")
                }
            }
        }
    }

    private fun writeUpgrades(
        printStream: OutputStream,
        result: Result
    ) = with(printStream) {
        val upgradeVersions = result.outdated.dependencies
        if (upgradeVersions.isNotEmpty()) {
            println()
            println("The following dependencies have later $revision versions:")
            for (dependency in upgradeVersions) {
                val currentVersion = dependency.version
                println(" - ${label(dependency)} [$currentVersion -> ${dependency.available[revision]}]")
                dependency.userReason?.let {
                    println("     $it")
                }
                dependency.projectUrl?.let {
                    println("     $it")
                }
            }
        }
    }

    private fun writeUndeclared(
        printStream: OutputStream,
        result: Result
    ) = with(printStream) {
        val undeclaredVersions = result.undeclared.dependencies
        if (undeclaredVersions.isNotEmpty()) {
            println()
            println(
                "Failed to compare versions for the following dependencies because they were declared without version:"
            )
            for (dependency in undeclaredVersions) {
                println(" - ${label(dependency)}")
            }
        }
    }

    private fun writeUnresolved(
        printStream: OutputStream,
        result: Result
    ) = with(printStream) {
        val unresolved = result.unresolved.dependencies
        if (unresolved.isNotEmpty()) {
            println()
            println(
                "Failed to determine the latest version for the following dependencies (use --info for details):"
            )
            for (dependency in unresolved) {
                println(" - " + label(dependency))
                dependency.userReason?.let {
                    println("     $it")
                }
                dependency.projectUrl?.let {
                    println("     $it")
                }
                project.logger.info(
                    "The exception that is the cause of unresolved state: {}",
                    dependency.reason
                )
            }
        }
    }

    private fun writeGradleUpdates(
        printStream: OutputStream,
        result: Result
    ) = with(printStream) {
        if (!result.gradle.enabled) {
            return
        }
        println()
        println("Gradle $gradleReleaseChannel updates:")
        // Log Gradle update checking failures.
        if (result.gradle.current.isFailure) {
            println("[ERROR] [release channel: ${CURRENT.id}] " + result.gradle.current.reason)
        }
        if ((gradleReleaseChannel == RELEASE_CANDIDATE.id || gradleReleaseChannel == NIGHTLY.id) &&
            result.gradle.releaseCandidate.isFailure
        ) {
            println(
                "[ERROR] [release channel: ${RELEASE_CANDIDATE.id}] " + result
                    .gradle.releaseCandidate.reason
            )
        }
        if (gradleReleaseChannel == NIGHTLY.id && result.gradle.nightly.isFailure) {
            println("[ERROR] [release channel: ${NIGHTLY.id}] " + result.gradle.nightly.reason)
        }
        // print Gradle updates in breadcrumb format
        print(" - Gradle: [" + result.gradle.running.version)
        var updatePrinted = false
        if (result.gradle.current.isUpdateAvailable && result.gradle.current > result.gradle.running) {
            updatePrinted = true
            print(" -> " + result.gradle.current.version)
        }
        if ((gradleReleaseChannel == RELEASE_CANDIDATE.id || gradleReleaseChannel == NIGHTLY.id) &&
            result.gradle.releaseCandidate.isUpdateAvailable &&
            result.gradle.releaseCandidate >
            result.gradle.current
        ) {
            updatePrinted = true
            print(" -> " + result.gradle.releaseCandidate.version)
        }
        if (gradleReleaseChannel == NIGHTLY.id &&
            result.gradle.nightly.isUpdateAvailable &&
            result.gradle.nightly >
            result.gradle.current
        ) {
            updatePrinted = true
            print(" -> " + result.gradle.nightly.version)
        }
        if (!updatePrinted) {
            print(": UP-TO-DATE")
        }
        println("]")
    }

    companion object {
        /** Returns the dependency key as a stringified label. */
        private fun label(dependency: Dependency): String {
            return "${dependency.group.orEmpty()}:${dependency.name}"
        }
    }
}
