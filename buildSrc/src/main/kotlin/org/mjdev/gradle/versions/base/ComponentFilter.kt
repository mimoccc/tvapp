/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.base

import org.gradle.api.HasImplicitReceiver

@HasImplicitReceiver
fun interface ComponentFilter {
    fun reject(candidate: ComponentSelectionWithCurrent): Boolean
}
