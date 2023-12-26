/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.gradle.plugin

//import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.*

class DependencyUpdatePlugin : Plugin<Project> {
    override fun apply(project: Project) = with(project) {
//        tasks.named("dependencyUpdates", DependencyUpdatesTask::class.java).configure {
//            rejectVersionIf {
//                isNonStable(candidate.version)
//            }
//            outputFormatter = "html"
//            doLast {
//                exec {
//                    commandLine("open", "build/dependencyUpdates/report.html")
//                }
//            }
//        }
    }

    private fun isNonStable(version: String): Boolean {
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any {
            version.uppercase(Locale.getDefault()).contains(it)
        }
        val regex = "^[0-9,.v-]+(-r)?$".toRegex()
        val isStable = stableKeyword || regex.matches(version)
        return isStable.not()
    }
}