/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.base

import org.gradle.api.Project
import org.gradle.api.internal.artifacts.ivyservice.ivyresolve.strategy.DefaultVersionComparator
import org.gradle.api.internal.artifacts.ivyservice.ivyresolve.strategy.VersionParser

class VersionMapping(
    val project: Project,
    statuses: Set<DependencyStatus>
) {
    val downgrade = sortedSetOf<Coordinate>()
    val upToDate = sortedSetOf<Coordinate>()
    val upgrade = sortedSetOf<Coordinate>()
    val undeclared = sortedSetOf<Coordinate>()
    val unresolved = sortedSetOf<Coordinate>()
    val current = sortedSetOf<Coordinate>()
    val latest = sortedSetOf<Coordinate>()
    private var comparator = makeVersionComparator()

    init {
        for (status in statuses) {
            current.add(status.coordinate)
            if (status.unresolved == null) {
                latest.add(status.getLatestCoordinate())
            } else {
                unresolved.add(status.coordinate)
            }
        }
        organize()
    }

    private fun organize() {
        val latestByKey = latest.associateBy({ it.key }, { it })
        for (coordinate in current) {
            val latestCoordinate = latestByKey[coordinate.key]
            val version = latestCoordinate?.version
            project.logger
                .info(
                    "Comparing dependency (current: {}, latest: {})",
                    coordinate,
                    version ?: "unresolved"
                )
            if (unresolved.contains(coordinate)) {
                continue
            } else if (coordinate.version == "none") {
                undeclared.add(coordinate)
                continue
            }
            val result = comparator.compare(coordinate.version, version)
            if (result <= -1) {
                upgrade.add(coordinate)
            } else if (result == 0) {
                upToDate.add(coordinate)
            } else {
                downgrade.add(coordinate)
            }
        }
    }

    companion object {
        private fun makeVersionComparator(): Comparator<String> {
            val baseComparator = DefaultVersionComparator().asVersionComparator()
            val versionParser = VersionParser()
            return Comparator { string1, string2 ->
                baseComparator.compare(
                    versionParser.transform(string1),
                    versionParser.transform(string2)
                )
            }
        }
    }
}
