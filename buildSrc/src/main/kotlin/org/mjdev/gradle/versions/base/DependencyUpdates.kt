/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.base

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.UnresolvedDependency
import org.gradle.api.specs.Spec

class DependencyUpdates @JvmOverloads constructor(
    val project: Project,
    private val resolutionStrategy: Action<in ResolutionStrategyWithCurrent>?,
    val revision: String,
    private val outputFormatterArgument: OutputFormatterArgument,
    private val outputDir: String,
    private val reportFileName: String?,
    private val checkForGradleUpdate: Boolean,
    private val gradleVersionsApiBaseUrl: String,
    private val gradleReleaseChannel: String,
    private val checkConstraints: Boolean = false,
    private val checkBuildEnvironmentConstraints: Boolean = false,
    private val filterConfigurations: Spec<Configuration> = Spec<Configuration> { true }
) {

    fun run(): DependencyUpdatesReporter {
        val projectConfigs = project.allprojects.associateBy(
            { it },
            { it.configurations.matching(filterConfigurations).toLinkedHashSet() }
        )
        val status: Set<DependencyStatus> = resolveProjects(projectConfigs, checkConstraints)
        val buildscriptProjectConfigs = project.allprojects.associateBy(
            { it },
            { it.buildscript.configurations.toLinkedHashSet() }
        )
        val buildscriptStatus: Set<DependencyStatus> = resolveProjects(
            buildscriptProjectConfigs, checkBuildEnvironmentConstraints
        )
        val statuses = status + buildscriptStatus
        val versions = VersionMapping(project, statuses)
        val unresolved = statuses.mapNotNullTo(mutableSetOf()) { it.unresolved }
        val projectUrls = statuses.filter {
            !it.projectUrl.isNullOrEmpty()
        }.associateBy(
            { mapOf("group" to it.coordinate.groupId, "name" to it.coordinate.artifactId) },
            { it.projectUrl.toString() }
        )
        return createReporter(versions, unresolved, projectUrls)
    }

    private fun resolveProjects(
        projectConfigs: Map<Project, Set<Configuration>>,
        checkConstraints: Boolean,
    ): Set<DependencyStatus> {
        val resultStatus = hashSetOf<DependencyStatus>()
        projectConfigs.forEach { (currentProject, currentConfigurations) ->
            val resolver = Resolver(currentProject, resolutionStrategy, checkConstraints)
            for (currentConfiguration in currentConfigurations) {
                if (currentConfiguration.isCanBeResolved) {
                    for (newStatus in resolve(resolver, currentProject, currentConfiguration)) {
                        addValidatedDependencyStatus(resultStatus, newStatus)
                    }
                }
            }
        }
        return resultStatus
    }

    private fun resolve(
        resolver: Resolver,
        project: Project,
        config: Configuration,
    ): Set<DependencyStatus> {
        return try {
            resolver.resolve(config, revision)
        } catch (e: Exception) {
            project.logger.info("Skipping configuration ${project.path}:${config.name}", e)
            emptySet()
        }
    }

    private fun createReporter(
        versions: VersionMapping,
        unresolved: Set<UnresolvedDependency>,
        projectUrls: Map<Map<String, String>, String>,
    ): DependencyUpdatesReporter {
        val currentVersions = versions.current            .associateBy(
            { mapOf("group" to it.groupId, "name" to it.artifactId) },
            { it }
        )
        val latestVersions = versions.latest.associateBy(
            { mapOf("group" to it.groupId, "name" to it.artifactId) },
            { it }
        )
        val upToDateVersions = versions.upToDate.associateBy(
            { mapOf("group" to it.groupId, "name" to it.artifactId) },
            { it }
        )
        val downgradeVersions = toMap(versions.downgrade)
        val upgradeVersions = toMap(versions.upgrade)
        return DependencyUpdatesReporter(
            project,
            revision,
            outputFormatterArgument,
            outputDir,
            reportFileName,
            currentVersions,
            latestVersions,
            upToDateVersions,
            downgradeVersions,
            upgradeVersions,
            versions.undeclared,
            unresolved,
            projectUrls,
            GradleUpdateChecker(checkForGradleUpdate, gradleVersionsApiBaseUrl),
            gradleReleaseChannel
        )
    }

    companion object {

        private fun addValidatedDependencyStatus(
            statusCollection: HashSet<DependencyStatus>,
            status: DependencyStatus,
        ) {
            val statusWithSameCoordinateKey = statusCollection.find {
                it.coordinate.key == status.coordinate.key
            }
            if (statusWithSameCoordinateKey == null) {
                statusCollection.add(status)
            } else if (status.coordinate.version != "none") {
                statusCollection.add(status)
                if (statusWithSameCoordinateKey.coordinate.version == "none") {
                    statusCollection.remove(statusWithSameCoordinateKey)
                }
            }
        }

        private fun toMap(coordinates: Set<Coordinate>): Map<Map<String, String>, Coordinate> {
            val map = HashMap<Map<String, String>, Coordinate>()
            for (coordinate in coordinates) {
                var i = 0
                while (true) {
                    val artifactId = coordinate.artifactId + if (i == 0) "" else "[${i + 1}]"
                    val keyMap = linkedMapOf<String, String>().apply {
                        put("group", coordinate.groupId)
                        put("name", artifactId)
                    }
                    if (!map.containsKey(keyMap)) {
                        map[keyMap] = coordinate
                        break
                    }
                    ++i
                }
            }
            return map
        }

    }

}

private fun <T> Collection<T>.toLinkedHashSet(): LinkedHashSet<T> {
    return toCollection(LinkedHashSet(this.size))
}
