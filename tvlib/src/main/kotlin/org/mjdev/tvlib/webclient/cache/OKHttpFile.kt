package org.mjdev.tvlib.webclient.cache

import android.text.TextUtils
import okio.ByteString.Companion.encodeUtf8
import okio.GzipSource
import okio.buffer
import okio.source
import timber.log.Timber
import java.io.*

object OKHttpFile {

    private const val ENTRY_METADATA = 0
    private const val ENTRY_BODY = 1

    fun getCacheFile(path: File, url: String?): InputStream? {
        if (TextUtils.isEmpty(url)) return null
        val key: String? = url?.encodeUtf8()?.md5()?.hex()
        val entryFile = File(path.absolutePath, "$key.$ENTRY_METADATA")
        val bodyFile = File(path.absolutePath, "$key.$ENTRY_BODY")
        if (entryFile.exists() && bodyFile.exists()) {
            try {
                val fr = BufferedReader(FileReader(entryFile), 1024)
                var line : String
                var isGzip = false
                while (fr.readLine().also { line = it } != null) {
                    if (line.contains("Content-Encoding") &&
                        line.contains("gzip")
                    ) {
                        isGzip = true
                        break
                    }
                }
                val inputStream: InputStream = FileInputStream(bodyFile)
                return if (!isGzip) inputStream
                else {
                    var source = bodyFile.source()
                    source = GzipSource(source)
                    source.buffer().inputStream()
                }
            } catch (e: Exception) {
              Timber.e(e)
            }
        }
        return null
    }

}