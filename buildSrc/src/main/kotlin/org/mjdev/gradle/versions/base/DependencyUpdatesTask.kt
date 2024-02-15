/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.base

import org.mjdev.gradle.versions.reporter.Reporter
import org.mjdev.gradle.versions.base.GradleReleaseChannel.RELEASE_CANDIDATE
import groovy.lang.Closure
import org.gradle.api.Action
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.artifacts.Configuration
import org.gradle.api.specs.Spec
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import javax.annotation.Nullable
import org.mjdev.gradle.versions.result.Result

@Suppress("unused")
open class DependencyUpdatesTask : DefaultTask() { // tasks can't be final

    @Input
    var revision: String = "release"
        get() = (System.getProperties()["revision"] ?: field) as String

    @Input
    var gradleReleaseChannel: String = RELEASE_CANDIDATE.id
        get() = (System.getProperties()["gradleReleaseChannel"] ?: field) as String

    @Suppress("DEPRECATION")
    @Input
    var outputDir: String =
        "${project.buildDir.path.replace(project.projectDir.path + "/", "")}/dependencyUpdates"
        get() = (System.getProperties()["outputDir"] ?: field) as String

    @Input
    @Optional
    var reportFileName: String = "report"
        get() = (System.getProperties()["reportFileName"] ?: field) as String

    var outputFormatter: Any?
        @Internal get() = null
        set(value) {
            outputFormatterArgument = when (value) {
                is String -> OutputFormatterArgument.BuiltIn(value)
                is Reporter -> OutputFormatterArgument.CustomReporter(value)
                // Kept for retro-compatibility with "outputFormatter = {}" usages.
                is Closure<*> -> OutputFormatterArgument.CustomAction {
                    value.call(this)
                }

                else -> throw IllegalArgumentException(
                    "Unsupported output formatter provided $value. Please use a String, a Reporter/Closure, " +
                            "or alternatively provide a function using the `outputFormatter(Action<Result>)` API."
                )
            }
        }

    private var outputFormatterArgument: OutputFormatterArgument = OutputFormatterArgument.DEFAULT

    @Input
    @Optional
    fun getOutputFormatterName(): String? {
        return with(outputFormatterArgument) {
            if (this is OutputFormatterArgument.BuiltIn) formatterNames
            else null
        }
    }

    @Input
    var checkForGradleUpdate: Boolean = true

    @Input
    var gradleVersionsApiBaseUrl: String = "https://services.gradle.org/versions/"

    @Input
    var checkConstraints: Boolean = false

    @Internal
    var filterConfigurations: Spec<Configuration> = Spec<Configuration> { true }

    @Input
    var checkBuildEnvironmentConstraints: Boolean = false

    @Internal
    @Nullable
    var resolutionStrategy: Closure<*>? = null

    @Nullable
    private var resolutionStrategyAction: Action<in ResolutionStrategyWithCurrent>? = null

    init {
        description = "Displays the dependency updates for the project."
        group = "mjdev"

        outputs.upToDateWhen { false }
        callIncompatibleWithConfigurationCache()
    }

    @TaskAction
    fun dependencyUpdates() {
        project.evaluationDependsOnChildren()
        if (resolutionStrategy != null) {
            val closure = resolutionStrategy!!
            resolutionStrategy {
                project.configure(this, closure)
            }
            logger.warn(
                "dependencyUpdates.resolutionStrategy: " +
                        "Remove the assignment operator, \"=\", when setting this task property"
            )
        }
        DependencyUpdates(
            project,
            resolutionStrategyAction,
            revision,
            outputFormatter(),
            outputDir,
            reportFileName,
            checkForGradleUpdate,
            gradleVersionsApiBaseUrl,
            gradleReleaseChannel,
            checkConstraints,
            checkBuildEnvironmentConstraints,
            filterConfigurations
        ).run().write()
    }

    fun rejectVersionIf(filter: ComponentFilter) {
        resolutionStrategy {
            componentSelection {
                all {
                    @Suppress("SENSELESS_COMPARISON")
                    val isNotNull =
                        currentVersion != null && candidate.version != null
                    if (isNotNull && filter.reject(this)) {
                        reject("Rejected by rejectVersionIf ")
                    }
                }
            }
        }
    }

    private fun resolutionStrategy(resolutionStrategy: Action<in ResolutionStrategyWithCurrent>? = null) {
        this.resolutionStrategyAction = resolutionStrategy
        this.resolutionStrategy = null
    }

    private fun outputFormatter(): OutputFormatterArgument {
        val outputFormatterProperty = System.getProperties()["outputFormatter"] as? String
        return outputFormatterProperty?.let { OutputFormatterArgument.BuiltIn(it) }
            ?: outputFormatterArgument
    }

    fun outputFormatter(action: Action<Result>) {
        outputFormatterArgument = OutputFormatterArgument.CustomAction(action)
    }

    private fun callIncompatibleWithConfigurationCache() {
        this::class.members.find { it.name == "notCompatibleWithConfigurationCache" }
            ?.call(this, "The gradle-versions-plugin isn't compatible with the configuration cache")
    }

}
