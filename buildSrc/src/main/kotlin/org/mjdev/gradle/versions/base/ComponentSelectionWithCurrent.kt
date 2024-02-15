/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.base

import org.gradle.api.artifacts.ComponentSelection

class ComponentSelectionWithCurrent(
    private val delegate: ComponentSelection,
    val currentVersion: String,
) : ComponentSelection by delegate {
    override fun toString(): String {
        return """\
ComponentSelectionWithCurrent{
    group="${candidate.group.orEmpty()}",
    module="${candidate.module}",
    version="${candidate.version}",
    currentVersion="$currentVersion",
}"""
    }
}
