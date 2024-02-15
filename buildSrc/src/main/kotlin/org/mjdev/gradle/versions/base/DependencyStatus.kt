/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.base

import org.gradle.api.artifacts.UnresolvedDependency

class DependencyStatus {
    val coordinate: Coordinate
    private val latestVersion: String
    val unresolved: UnresolvedDependency?
    val projectUrl: String?

    constructor(
        coordinate: Coordinate,
        latestVersion: String,
        projectUrl: String?
    ) {
        this.coordinate = coordinate
        this.latestVersion = latestVersion
        this.projectUrl = projectUrl
        this.unresolved = null
    }

    constructor(
        coordinate: Coordinate,
        unresolved: UnresolvedDependency?
    ) {
        this.coordinate = coordinate
        this.unresolved = unresolved
        latestVersion = "none"
        projectUrl = null
    }

    fun getLatestCoordinate(): Coordinate {
        return Coordinate(
            coordinate.groupId,
            coordinate.artifactId,
            latestVersion,
            coordinate.userReason
        )
    }
}
