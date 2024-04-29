/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.glide.sources.svg

import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import com.bumptech.glide.request.target.Target
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

/**
 * Decodes an SVG internal representation from an [InputStream].
 */
class SvgDecoder : ResourceDecoder<InputStream, SVG> {

    /**
     * Handles indicator, see glide lib documentation
     * @param source InputStream
     * @param options Options
     * @return Boolean
     */
    override fun handles(source: InputStream, options: Options): Boolean {
        return isSVG(source)
    }

    /**
     * Check if model represents svg file
     * @param source InputStream
     * @return Boolean
     */
    private fun isSVG(source: InputStream): Boolean {
        return InputStreamReader(source).readText().contains("<svg")
    }

    /**
     * Decode svg to bitmap
     * @param source InputStream
     * @param width Int
     * @param height Int
     * @param options Options
     * @return Resource<SVG>
     * @throws IOException
     */
    @Throws(IOException::class)
    override fun decode(
        source: InputStream,
        width: Int,
        height: Int,
        options: Options
    ): Resource<SVG> {
        return try {
            val svg = SVG.getFromInputStream(source)
            if (width != Target.SIZE_ORIGINAL) svg.documentWidth = width.toFloat()
            if (height != Target.SIZE_ORIGINAL) svg.documentHeight = height.toFloat()
            SimpleResource(svg)
        } catch (ex: SVGParseException) {
            throw IOException("Cannot load SVG from stream", ex)
        }
    }

}
