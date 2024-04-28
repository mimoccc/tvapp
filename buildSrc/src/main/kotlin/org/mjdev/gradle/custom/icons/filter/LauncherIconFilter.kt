/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.custom.icons.filter

import java.awt.image.BufferedImage

interface LauncherIconFilter {
    fun setAdaptiveLauncherMode(enable: Boolean)
    fun apply(image: BufferedImage)
}