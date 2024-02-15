/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.base

import org.gradle.util.GradleVersion

class GradleUpdateResult(
    enabled: Boolean = false,
    running: ReleaseStatus.Available? = null,
    release: ReleaseStatus? = null,
) : Comparable<GradleUpdateResult> {
    var version: String
    var isUpdateAvailable: Boolean
    var isFailure: Boolean
    var reason: String

    init {
        if (!enabled) {
            version = ""
            isUpdateAvailable = false
            isFailure = false
            reason = "update check disabled"
        } else if (release is ReleaseStatus.Available) {
            version = release.gradleVersion.version
            isUpdateAvailable = release.gradleVersion > running!!.gradleVersion
            isFailure = false
            reason = "" // empty string so the field is serialized
        } else if (release is ReleaseStatus.Unavailable) {
            version = "" // empty string so the field is serialized
            isUpdateAvailable = false
            isFailure = false
            reason = "update check succeeded: no release available"
        } else if (release is ReleaseStatus.Failure) {
            version = "" // empty string so the field is serialized
            isUpdateAvailable = false
            isFailure = true
            reason = release.reason
        } else {
            throw IllegalStateException(
                "ReleaseStatus subtype [" + release!!.javaClass + "] not yet implemented"
            )
        }
    }

    override fun compareTo(other: GradleUpdateResult): Int {
        return comparator.compare(this, other)
    }

    companion object {
        private val comparator = Comparator.comparing { gradleUpdateResult: GradleUpdateResult ->
            GradleVersion.version(gradleUpdateResult.version)
        }
    }
}
