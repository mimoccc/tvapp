/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.base

import org.mjdev.gradle.versions.reporter.HtmlReporter
import org.mjdev.gradle.versions.reporter.JsonReporter
import org.mjdev.gradle.versions.reporter.PlainTextReporter
import org.mjdev.gradle.versions.reporter.Reporter
import org.mjdev.gradle.versions.reporter.XmlReporter
import org.mjdev.gradle.versions.result.DependenciesGroup
import org.mjdev.gradle.versions.result.Dependency
import org.mjdev.gradle.versions.result.DependencyLatest
import org.mjdev.gradle.versions.result.DependencyOutdated
import org.mjdev.gradle.versions.result.DependencyUnresolved
import org.mjdev.gradle.versions.result.Result
import org.mjdev.gradle.versions.result.VersionAvailable
import org.gradle.api.Project
import org.gradle.api.artifacts.ModuleVersionSelector
import org.gradle.api.artifacts.UnresolvedDependency
import java.io.File
import java.io.PrintStream
import java.io.PrintWriter
import java.io.StringWriter
import java.util.TreeSet

class DependencyUpdatesReporter(
    val project: Project,
    val revision: String,
    private val outputFormatterArgument: OutputFormatterArgument,
    private val outputDir: String,
    private val reportFileName: String?,
    private val currentVersions: Map<Map<String, String>, Coordinate>,
    private val latestVersions: Map<Map<String, String>, Coordinate>,
    private val upToDateVersions: Map<Map<String, String>, Coordinate>,
    private val downgradeVersions: Map<Map<String, String>, Coordinate>,
    private val upgradeVersions: Map<Map<String, String>, Coordinate>,
    private val undeclared: Set<Coordinate>,
    private val unresolved: Set<UnresolvedDependency>,
    private val projectUrls: Map<Map<String, String>, String>,
    private val gradleUpdateChecker: GradleUpdateChecker,
    private val gradleReleaseChannel: String,
) {

    @Synchronized
    fun write() {
        if (outputFormatterArgument !is OutputFormatterArgument.CustomAction) {
            val plainTextReporter = PlainTextReporter(
                project, revision, gradleReleaseChannel
            )
            plainTextReporter.write(System.out, buildBaseObject())
        }
        if (outputFormatterArgument is OutputFormatterArgument.BuiltIn && outputFormatterArgument.formatterNames.isEmpty()) {
            project.logger.lifecycle("Skip generating report to file (outputFormatter is empty)")
            return
        }
        when (outputFormatterArgument) {
            is OutputFormatterArgument.BuiltIn -> {
                for (it in outputFormatterArgument.formatterNames.split(",")) {
                    generateFileReport(getOutputReporter(it))
                }
            }
            is OutputFormatterArgument.CustomReporter -> {
                generateFileReport(outputFormatterArgument.reporter)
            }
            is OutputFormatterArgument.CustomAction -> {
                val result = buildBaseObject()
                outputFormatterArgument.action.execute(result)
            }
        }
    }

    private fun generateFileReport(reporter: Reporter) {
        val fileName = File(outputDir, reportFileName + "." + reporter.getFileExtension())
        project.file(outputDir).mkdirs()
        val outputFile = project.file(fileName)
        val stream = PrintStream(outputFile)
        val result = buildBaseObject()
        reporter.write(stream, result)
        stream.close()
        project.logger.lifecycle("\nGenerated report file $fileName")
    }

    private fun getOutputReporter(formatterOriginal: String): Reporter {
        return when (formatterOriginal.trim()) {
            "json" -> JsonReporter(project, revision, gradleReleaseChannel)
            "xml" -> XmlReporter(project, revision, gradleReleaseChannel)
            "html" -> HtmlReporter(project, revision, gradleReleaseChannel)
            else -> PlainTextReporter(project, revision, gradleReleaseChannel)
        }
    }

    private fun buildBaseObject(): Result {
        val sortedCurrent = buildCurrentGroup()
        val sortedOutdated = buildOutdatedGroup()
        val sortedExceeded = buildExceededGroup()
        val sortedUndeclared = buildUndeclaredGroup()
        val sortedUnresolved = buildUnresolvedGroup()
        val count = sortedCurrent.size +
                sortedOutdated.size +
                sortedExceeded.size +
                sortedUndeclared.size +
                sortedUnresolved.size
        return buildObject(
            count = count,
            currentGroup = buildDependenciesGroup(sortedCurrent),
            outdatedGroup = buildDependenciesGroup(sortedOutdated),
            exceededGroup = buildDependenciesGroup(sortedExceeded),
            undeclaredGroup = buildDependenciesGroup(sortedUndeclared),
            unresolvedGroup = buildDependenciesGroup(sortedUnresolved),
            gradleUpdateResults = buildGradleUpdateResults(),
        )
    }

    private fun buildGradleUpdateResults(): GradleUpdateResults {
        val enabled = gradleUpdateChecker.enabled
        return GradleUpdateResults(
            enabled = enabled,
            running = GradleUpdateResult(
                enabled = enabled,
                running = gradleUpdateChecker.getRunningGradleVersion(),
                release = gradleUpdateChecker.getRunningGradleVersion(),
            ),
            current = GradleUpdateResult(
                enabled = enabled,
                running = gradleUpdateChecker.getRunningGradleVersion(),
                release = gradleUpdateChecker.getCurrentGradleVersion(),
            ),
            releaseCandidate = GradleUpdateResult(
                enabled = enabled && (
                        gradleReleaseChannel == GradleReleaseChannel.RELEASE_CANDIDATE.id ||
                                gradleReleaseChannel == GradleReleaseChannel.NIGHTLY.id
                        ),
                running = gradleUpdateChecker.getRunningGradleVersion(),
                release = gradleUpdateChecker.getReleaseCandidateGradleVersion(),
            ),
            nightly = GradleUpdateResult(
                enabled = enabled && (gradleReleaseChannel == GradleReleaseChannel.NIGHTLY.id),
                running = gradleUpdateChecker.getRunningGradleVersion(),
                release = gradleUpdateChecker.getNightlyGradleVersion(),
            ),
        )
    }

    private fun buildCurrentGroup(): MutableSet<Dependency> =
        sortByGroupAndName(upToDateVersions).map { dep ->
            updateKey(dep.key as HashMap)
            buildDependency(dep.value, dep.key)
        }.toSortedSet()

    private fun buildOutdatedGroup(): MutableSet<DependencyOutdated> =
        sortByGroupAndName(upgradeVersions).map { dep ->
            updateKey(dep.key as HashMap)
            buildOutdatedDependency(dep.value, dep.key)
        }.toSortedSet()


    private fun buildExceededGroup(): MutableSet<DependencyLatest> =
        sortByGroupAndName(downgradeVersions).map { dep ->
            updateKey(dep.key as HashMap)
            buildExceededDependency(dep.value, dep.key)
        }.toSortedSet()

    private fun buildUndeclaredGroup(): MutableSet<Dependency> = undeclared.map { coordinate ->
        Dependency(coordinate.groupId, coordinate.artifactId)
    }.toSortedSet()

    private fun buildUnresolvedGroup(): MutableSet<DependencyUnresolved> =
        unresolved.sortedWith { a, b ->
            compareKeys(keyOf(a.selector), keyOf(b.selector))
        }.map { dep ->
            val stringWriter = StringWriter()
            dep.problem.printStackTrace(PrintWriter(stringWriter))
            val message = stringWriter.toString()
            buildUnresolvedDependency(dep.selector, message)
        }.toSortedSet() as TreeSet<DependencyUnresolved>

    private fun buildDependency(
        coordinate: Coordinate,
        key: Map<String, String>
    ): Dependency = Dependency(
        group = key["group"],
        name = key["name"],
        version = coordinate.version,
        projectUrl = projectUrls[key],
        userReason = coordinate.userReason,
    )

    private fun buildExceededDependency(
        coordinate: Coordinate,
        key: Map<String, String>
    ): DependencyLatest = DependencyLatest(
        group = key["group"],
        name = key["name"],
        version = coordinate.version,
        projectUrl = projectUrls[key],
        userReason = coordinate.userReason,
        latest = latestVersions[key]?.version.orEmpty(),
    )

    private fun buildUnresolvedDependency(
        selector: ModuleVersionSelector,
        message: String
    ): DependencyUnresolved = DependencyUnresolved(
        group = selector.group,
        name = selector.name,
        version = currentVersions[keyOf(selector)]?.version,
        projectUrl = latestVersions[keyOf(selector)]?.version, // TODO not sure?
        userReason = currentVersions[keyOf(selector)]?.userReason,
        reason = message,
    )

    private fun buildOutdatedDependency(
        coordinate: Coordinate,
        key: Map<String, String>
    ): DependencyOutdated {
        val laterVersion = latestVersions[key]?.version
        val available = when (revision) {
            "milestone" -> VersionAvailable(milestone = laterVersion)
            "integration" -> VersionAvailable(integration = laterVersion)
            else -> VersionAvailable(release = laterVersion)
        }
        return DependencyOutdated(
            group = key["group"],
            name = key["name"],
            version = coordinate.version,
            projectUrl = projectUrls[key],
            userReason = coordinate.userReason,
            available = available,
        )
    }

    companion object {

        private fun updateKey(existingKey: HashMap<String, String>) {
            val index = existingKey["name"]?.lastIndexOf("[") ?: -1
            if (index == -1) {
                existingKey["name"] = existingKey["name"].orEmpty()
            } else {
                existingKey["name"] = existingKey["name"].orEmpty().substring(0, index)
            }
        }

        private fun buildObject(
            count: Int,
            currentGroup: DependenciesGroup<Dependency>,
            outdatedGroup: DependenciesGroup<DependencyOutdated>,
            exceededGroup: DependenciesGroup<DependencyLatest>,
            undeclaredGroup: DependenciesGroup<Dependency>,
            unresolvedGroup: DependenciesGroup<DependencyUnresolved>,
            gradleUpdateResults: GradleUpdateResults,
        ): Result = Result(
            count = count,
            current = currentGroup,
            outdated = outdatedGroup,
            exceeded = exceededGroup,
            undeclared = undeclaredGroup,
            unresolved = unresolvedGroup,
            gradle = gradleUpdateResults
        )

        private fun <T : Dependency> buildDependenciesGroup(
            dependencies: MutableSet<T>
        ): DependenciesGroup<T> = DependenciesGroup(dependencies.size, dependencies)

        private fun sortByGroupAndName(
            dependencies: Map<Map<String, String>, Coordinate>
        ): Map<Map<String, String>, Coordinate> = dependencies.toSortedMap { a, b ->
            compareKeys(a, b)
        }

        private fun compareKeys(
            a: Map<String, String>,
            b: Map<String, String>
        ): Int = if (a["group"] == b["group"]) {
            a["name"].orEmpty().compareTo(b["name"].orEmpty())
        } else {
            a["group"].orEmpty().compareTo(b["group"].orEmpty())
        }

        private fun keyOf(dependency: ModuleVersionSelector): Map<String, String> = mapOf(
            "group" to dependency.group, "name" to dependency.name
        )

    }

}
