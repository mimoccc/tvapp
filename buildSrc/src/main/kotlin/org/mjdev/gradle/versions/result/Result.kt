/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.result

import org.mjdev.gradle.versions.base.GradleUpdateResults

class Result(
    val count: Int,
    val current: DependenciesGroup<Dependency>,
    val outdated: DependenciesGroup<DependencyOutdated>,
    val exceeded: DependenciesGroup<DependencyLatest>,
    val undeclared: DependenciesGroup<Dependency>,
    val unresolved: DependenciesGroup<DependencyUnresolved>,
    val gradle: GradleUpdateResults,
)
