package org.mjdev.gradle.icons.filter

import java.awt.image.BufferedImage

interface LauncherIconFilter {
    fun setAdaptiveLauncherMode(enable: Boolean)
    fun apply(image: BufferedImage)
}