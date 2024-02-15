package org.mjdev.tvlib.ui.components.audiopreview

interface IPreviewEngine {

    fun searchAndPlayIfFound(
        filePath: String?,
        muted: Boolean = true,
        success: (() -> Unit)? = null,
        error: ((error: Exception) -> Unit)? = null
    )

    fun pause()

    fun stop()

    fun resume()

    fun seekTo(seek:Long)

    fun release()

}