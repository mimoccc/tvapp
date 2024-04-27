package org.mjdev.gradle.icons

import org.mjdev.gradle.icons.filter.LauncherIconFilter
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.util.stream.Stream
import javax.imageio.ImageIO

class LauncherIcon(
    inputFile: File,
    val outputFile: File
) {

    private val image: BufferedImage = ImageIO.read(inputFile)

    @Throws(IOException::class)
    fun save() {
        outputFile.parentFile.mkdirs()
        ImageIO.write(image, "png", outputFile)
        println("saving icon : ${File("$outputFile.png").absolutePath}")
    }

    fun process(filters: Stream<LauncherIconFilter>) {
        filters.forEach { filter: LauncherIconFilter ->
            filter.apply(image)
        }
    }

}
