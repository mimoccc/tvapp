/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.base

class GradleUpdateResults(
    val enabled: Boolean = false,
    val running: GradleUpdateResult,
    val current: GradleUpdateResult,
    val releaseCandidate: GradleUpdateResult,
    val nightly: GradleUpdateResult,
)
