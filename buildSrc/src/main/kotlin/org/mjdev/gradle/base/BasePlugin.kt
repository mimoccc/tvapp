/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.base

import org.gradle.api.NamedDomainObjectCollection
import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class BasePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.work()
    }

    abstract fun Project.work()

    operator fun <T> NamedDomainObjectCollection<T>.get(index:String) = findByName(index)

}
