/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.extensions

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.internal.catalog.ExternalModuleDependencyFactory
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.DependencyHandlerScope

private const val IMPLEMENTATION = "implementation"
private const val DEBUG_IMPLEMENTATION = "debugImplementation"
private const val ANDROID_TEST_IMPLEMENTATION = "androidTestImplementation"
private const val TEST_IMPLEMENTATION = "testImplementation"
private const val KSP_IMPLEMENTATION = "ksp"

fun DependencyHandlerScope.implementation(
    dependency: Provider<MinimalExternalModuleDependency>
) {
    add(IMPLEMENTATION, dependency.get())
}

fun DependencyHandlerScope.implementation(
    dependency: ExternalModuleDependencyFactory.DependencyNotationSupplier
) {
    add(IMPLEMENTATION, dependency.asProvider().get())
}

fun DependencyHandlerScope.implementation(
    dependency: Dependency
) {
    add(IMPLEMENTATION, dependency)
}

fun DependencyHandlerScope.debugImplementation(
    dependency: Provider<MinimalExternalModuleDependency>
) {
    add(DEBUG_IMPLEMENTATION, dependency.get())
}

fun DependencyHandlerScope.androidTestImplementation(
    dependency: Provider<MinimalExternalModuleDependency>
) {
    add(ANDROID_TEST_IMPLEMENTATION, dependency.get())
}

fun DependencyHandlerScope.testImplementation(
    dependency: Provider<MinimalExternalModuleDependency>
) {
    add(TEST_IMPLEMENTATION, dependency.get())
}

fun DependencyHandlerScope.ksp(
    dependency: Provider<MinimalExternalModuleDependency>
) {
    add(KSP_IMPLEMENTATION, dependency.get())
}

fun DependencyHandlerScope.ksp(
    dependency: Dependency
) {
    add(KSP_IMPLEMENTATION, dependency)
}
