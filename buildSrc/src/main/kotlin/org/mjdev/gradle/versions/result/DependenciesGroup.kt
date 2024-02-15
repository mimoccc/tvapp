/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.result

data class DependenciesGroup<T : Dependency>(
    val count: Int,
    val dependencies: MutableSet<T> = mutableSetOf(),
)
