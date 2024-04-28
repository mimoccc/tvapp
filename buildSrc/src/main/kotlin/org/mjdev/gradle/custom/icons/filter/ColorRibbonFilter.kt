/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.custom.icons.filter

import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.font.FontRenderContext
import java.awt.geom.AffineTransform
import java.awt.image.BufferedImage
import kotlin.math.pow
import kotlin.math.sqrt

class ColorRibbonFilter(
    private val label: String?,
    private val ribbonColor: Color,
    private val labelColor: Color = Color.WHITE,
    private val gravity: Gravity = Gravity.TOP_LEFT,
    private val textSizeRatio: Float = -1f,
    private val fontName: String = "DEFAULT",
    private val fontStyle: Int = Font.PLAIN,
    private var largeRibbon: Boolean = false
) : LauncherIconFilter {

    enum class Gravity {
        TOP, BOTTOM, TOP_LEFT, TOP_RIGHT
    }

    override fun setAdaptiveLauncherMode(enable: Boolean) {
        largeRibbon = enable
    }

    override fun apply(image: BufferedImage) {
        val imageWidth = image.width
        val imageHeight = image.height
        val g = image.graphics as Graphics2D
        when (gravity) {
            Gravity.TOP,
            Gravity.BOTTOM -> {
            }

            Gravity.TOP_RIGHT -> g.transform = AffineTransform.getRotateInstance(
                Math.toRadians(45.0),
                imageWidth.toDouble(),
                0.0
            )

            Gravity.TOP_LEFT -> g.transform = AffineTransform.getRotateInstance(
                Math.toRadians(-45.0)
            )
        }
        val frc = FontRenderContext(g.transform, true, true)
        val maxLabelWidth = calculateMaxLabelWidth(imageHeight / 2)
        g.font = getFont(imageHeight, maxLabelWidth, frc)
        val textBounds = g.font.getStringBounds(label ?: "", frc)
        val textHeight = textBounds.height.toInt()
        val textPadding = textHeight / 10
        val labelHeight = textHeight + textPadding * 2
        val y: Int = when (gravity) {
            Gravity.TOP -> if (largeRibbon) imageHeight / 4 else 0
            Gravity.BOTTOM -> imageHeight - labelHeight - (if (largeRibbon) imageHeight / 4 else 0)
            Gravity.TOP_RIGHT, Gravity.TOP_LEFT -> imageHeight / (if (largeRibbon) 2 else 4)
        }
        g.color = ribbonColor
        when (gravity) {
            Gravity.TOP, Gravity.BOTTOM -> {
                g.fillRect(0, y, imageWidth, labelHeight)
            }
            Gravity.TOP_RIGHT -> {
                g.fillRect(0, y, imageWidth * 2, labelHeight)
            }
            else -> {
                g.fillRect(-imageWidth, y, imageWidth * 2, labelHeight)
            }
        }
        if (label != null) {
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
            g.color = labelColor
            val fm = g.fontMetrics
            when (gravity) {
                Gravity.TOP, Gravity.BOTTOM -> {
                    g.drawString(
                        label,
                        imageWidth / 2 - textBounds.width.toInt() / 2,
                        y + fm.ascent
                    )
                }
                Gravity.TOP_RIGHT -> {
                    g.drawString(
                        label,
                        imageWidth - textBounds.width.toInt() / 2,
                        y + fm.ascent
                    )
                }
                else -> {
                    g.drawString(
                        label,
                        (-textBounds.width).toInt() / 2,
                        y + fm.ascent
                    )
                }
            }
        }
        g.dispose()
    }

    private fun getFont(imageHeight: Int, maxLabelWidth: Int, frc: FontRenderContext): Font {
        if (textSizeRatio != -1f) {
            return Font(fontName, fontStyle, (imageHeight * textSizeRatio).toInt())
        }
        var max = imageHeight / 8
        var min = 0
        if (label == null) {
            return Font(fontName, fontStyle, max / 2)
        }
        var size = max
        for (i in 0..9) {
            val mid = (max + min) / 2
            if (mid == size) {
                break
            }
            val font = Font(fontName, fontStyle, mid)
            val labelBounds = font.getStringBounds(label, frc)
            if (labelBounds.width.toInt() > maxLabelWidth) {
                max = mid
            } else {
                min = mid
            }
            size = mid
        }
        return Font(fontName, fontStyle, size)
    }

    private fun calculateMaxLabelWidth(y: Int): Int {
        return sqrt(y.toDouble().pow(2.0) * 2).toInt()
    }

}