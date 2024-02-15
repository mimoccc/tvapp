/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.base

import org.gradle.util.GradleVersion

sealed class ReleaseStatus {
    class Available(val gradleVersion: GradleVersion) : ReleaseStatus()

    object Unavailable : ReleaseStatus()

    class Failure(val reason: String) : ReleaseStatus()
}