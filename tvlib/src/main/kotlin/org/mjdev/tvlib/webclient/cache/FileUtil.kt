package org.mjdev.tvlib.webclient.cache

import android.text.TextUtils
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

object FileUtil {

    fun deleteDirs(path: String, isDeleteDir: Boolean) {
        if (TextUtils.isEmpty(path)) return
        val dir = File(path)
        if (!dir.exists()) return
        val files = dir.listFiles() ?: emptyArray()
        for (file in files) {
            if (file.isDirectory) deleteDirs(file.absolutePath, isDeleteDir)
            else file.delete()
        }
        if (isDeleteDir) dir.delete()
    }

    @Throws(IOException::class)
    fun copy(inputStream: InputStream, out: OutputStream) {
        val buf = ByteArray(2048)
        var len: Int
        while (inputStream.read(buf).also { readCnt -> len = readCnt } != -1) {
            out.write(buf, 0, len)
        }
        inputStream.close()
        out.close()
    }

}