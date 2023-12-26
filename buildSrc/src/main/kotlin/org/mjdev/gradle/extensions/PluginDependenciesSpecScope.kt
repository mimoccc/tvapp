/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.gradle.extensions

import org.gradle.kotlin.dsl.PluginDependenciesSpecScope
import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

fun PluginDependenciesSpec.ksp(): PluginDependencySpec =
    id("com.google.devtools.ksp")

fun PluginDependenciesSpecScope.hilt(): PluginDependencySpec =
    id("dagger.hilt.android.plugin")

fun PluginDependenciesSpecScope.androidHilt(): PluginDependencySpec =
    id("com.google.dagger.hilt.android")

fun PluginDependenciesSpecScope.objectBox(): PluginDependencySpec =
    id("io.objectbox")

fun PluginDependenciesSpecScope.kotlinAndroid(): PluginDependencySpec =
    kotlin("android")

fun PluginDependenciesSpecScope.kotlinKapt(): PluginDependencySpec =
    kotlin("kapt")

fun PluginDependenciesSpecScope.kotlinParcelize(): PluginDependencySpec =
    id("kotlin-parcelize")

fun PluginDependenciesSpecScope.androidLibrary(): PluginDependencySpec =
    id("com.android.library")

fun PluginDependenciesSpecScope.androidApplication(): PluginDependencySpec =
    id("com.android.application")

fun PluginDependenciesSpecScope.mainAppPlugin(): PluginDependencySpec =
    id("MainAppPlugin")