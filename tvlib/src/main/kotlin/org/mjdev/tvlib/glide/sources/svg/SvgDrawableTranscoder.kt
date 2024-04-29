/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.glide.sources.svg

import android.graphics.Picture
import android.graphics.drawable.PictureDrawable
import androidx.annotation.Keep
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.SimpleResource
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder
import com.caverock.androidsvg.SVG

/**
 * Convert the [SVG]'s internal representation to an Android-compatible one ([Picture]).
 */
@Keep
class SvgDrawableTranscoder : ResourceTranscoder<SVG?, PictureDrawable?> {

    /**
     * Transcode data
     * @param toTranscode Resource<SVG?>
     * @param options Options
     * @return Resource<PictureDrawable?>
     */
    override fun transcode(
            toTranscode: Resource<SVG?>,
            options: Options
    ): Resource<PictureDrawable?> {
        val svg: SVG = toTranscode.get()
        val picture: Picture = svg.renderToPicture()
        val drawable = PictureDrawable(picture)
        return SimpleResource(drawable)
    }

}
