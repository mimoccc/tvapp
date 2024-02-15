/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.result

open class Dependency @JvmOverloads constructor(
    open val group: String? = null,
    open val name: String? = null,
    open val version: String? = null,
    open val projectUrl: String? = null,
    open val userReason: String? = null,
) : Comparable<Dependency> {
    override fun compareTo(other: Dependency): Int {
        return compareValuesBy(
            this,
            other,
            { it.group },
            { it.name },
            { it.version },
            { it.projectUrl },
            { it.userReason },
        )
    }
}
