package org.mjdev.gradle.versions.base

enum class GradleReleaseChannel(val id: String) {
    CURRENT("current"),
    RELEASE_CANDIDATE("release-candidate"),
    NIGHTLY("nightly"),
}
