/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.icons.filter

import java.awt.image.BufferedImage

class GrayscaleFilter : LauncherIconFilter {
    override fun setAdaptiveLauncherMode(enable: Boolean) {}

    override fun apply(image: BufferedImage) {
        val width = image.width
        val height = image.height
        for (y in 0 until height) {
            for (x in 0 until width) {
                val color = image.getRGB(x, y)
                image.setRGB(x, y, toGray(color))
            }
        }
    }

    companion object {
        private fun toGray(color: Int): Int {
            val a = color and -0x1000000
            val r = color and 0x00FF0000 shr 16
            val g = color and 0x0000FF00 shr 8
            val b = color and 0x000000FF
            val c = ((2.0 * r + 4.0 * g + b) / 7.0).toInt()
            return a or (c shl 16) or (c shl 8) or c
        }
    }
}