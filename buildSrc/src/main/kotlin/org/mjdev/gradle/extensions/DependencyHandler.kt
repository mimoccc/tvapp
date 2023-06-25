/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.gradle.extensions

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.debugImplementation(dependencyNotation: String): Dependency? =
    add("debugImplementation", dependencyNotation)

fun DependencyHandler.releaseImplementation(dependencyNotation: String): Dependency? =
    add("releaseImplementation", dependencyNotation)

fun DependencyHandler.testImplementation(dependencyNotation: String): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.androidTestImplementation(dependencyNotation: String): Dependency? =
    add("androidTestImplementation", dependencyNotation)

fun DependencyHandler.androidTestImplementation(dependency: Dependency): Dependency? =
    add("androidTestImplementation", dependency)

fun DependencyHandler.testAnnotationProcessor(dependencyNotation: String): Dependency? =
    add("testAnnotationProcessor", dependencyNotation)

fun DependencyHandler.androidTestAnnotationProcessor(dependencyNotation: String): Dependency? =
    add("androidTestAnnotationProcessor", dependencyNotation)

fun DependencyHandler.implementation(dependencyNotation: String): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.implementation(dependency: Dependency): Dependency? =
    add("implementation", dependency)

fun DependencyHandler.kapt(dependencyNotation: String): Dependency? =
    add("kapt", dependencyNotation)

fun DependencyHandler.ksp(dependencyNotation: String): Dependency? =
    add("ksp", dependencyNotation)