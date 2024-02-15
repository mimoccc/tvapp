/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.icons.filter

import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO
import kotlin.math.min

class OverlayFilter(
        private val fgFile: File
) : LauncherIconFilter {
    private var addPadding = false

    override fun setAdaptiveLauncherMode(enable: Boolean) {
        addPadding = enable
    }

    override fun apply(image: BufferedImage) {
        var fgImage: Image? = null
        try {
            fgImage = ImageIO.read(fgFile)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        if (fgImage != null) {
            val width = image.width
            val height = image.width
            var scale = min(width / fgImage.getWidth(null).toFloat(), height / fgImage.getHeight(null).toFloat())
            if (addPadding) scale *= (72f / 108)
            val fgImageScaled = fgImage.getScaledInstance(
                    (fgImage.getWidth(null) * scale).toInt(),
                    (fgImage.getWidth(null) * scale).toInt(),
                    Image.SCALE_SMOOTH)
            val g = image.createGraphics()
            if (addPadding) g.drawImage(fgImageScaled, (width * (1 - 72f / 108) / 2).toInt(), (height * (1 - 72f / 108) / 2).toInt(), null) else g.drawImage(fgImageScaled, 0, 0, null)
            g.dispose()
        }
    }
}