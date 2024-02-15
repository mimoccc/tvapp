/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.result

data class DependencyUnresolved(
    override val group: String? = null,
    override val name: String? = null,
    override val version: String? = null,
    override val projectUrl: String? = null,
    override val userReason: String? = null,
    val reason: String,
) : Dependency()
