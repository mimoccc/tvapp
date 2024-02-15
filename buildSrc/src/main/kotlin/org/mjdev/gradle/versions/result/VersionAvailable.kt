/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.result

class VersionAvailable @JvmOverloads constructor(
    val release: String? = null,
    val milestone: String? = null,
    val integration: String? = null,
) {
    operator fun get(revision: String): String? = when (revision) {
        "release" -> release
        "milestone" -> milestone
        "integration" -> integration
        else -> ""
    }
}
