package org.mjdev.tvlib.ui.components.audiopreview

interface IPreviewEngine {

    fun searchAndPlayIfFound(
        filePath: String?,
        muted: Boolean = true,
        success: () -> Unit = {},
        error: (error: Exception) -> Unit = {}
    )

    fun pause()

    fun resume()

    fun seekTo(seek:Long)

    fun release()

}