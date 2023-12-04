package org.mjdev.tvlib.webclient.cache

import android.content.Context
import android.text.TextUtils
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL
import java.util.*
import java.util.concurrent.CopyOnWriteArraySet

class AssetsLoader {

    private var context: Context? = null
    private var assetResSet: CopyOnWriteArraySet<String>? = null
    private var dir = ""
    private var cleared = false
    private var isSuffixMod = false

    fun isAssetsSuffixMod(suffixMod: Boolean): AssetsLoader {
        isSuffixMod = suffixMod
        return this
    }

    fun init(context: Context?): AssetsLoader {
        this.context = context
        assetResSet = CopyOnWriteArraySet()
        cleared = false
        return this
    }

    private fun getUrlPath(url: String): String {
        var uPath = ""
        try {
            val u = URL(url)
            uPath = u.path
            if (uPath.startsWith("/")) {
                if (uPath.length == 1) {
                    return uPath
                }
                uPath = uPath.substring(1)
            }
        } catch (e: MalformedURLException) {
            Timber.e(e)
        }
        return uPath
    }

    fun getResByUrl(url: String): InputStream? {
        val uPath = getUrlPath(url)
        if (TextUtils.isEmpty(uPath))
            return null
        if (!isSuffixMod) {
            return if (TextUtils.isEmpty(dir)) {
                getAssetFileStream(uPath)
            } else {
                getAssetFileStream(dir + File.separator + uPath)
            }
        }
        if (assetResSet != null) {
            for (p in assetResSet!!) {
                if (uPath.endsWith(p)) {
                    return if (TextUtils.isEmpty(dir)) {
                        getAssetFileStream(p)
                    } else {
                        getAssetFileStream(dir + File.separator + p)
                    }
                }
            }
        }
        return null
    }

    fun setDir(dir: String): AssetsLoader {
        this.dir = dir
        return this
    }

    fun initData(): AssetsLoader {
        if (!isSuffixMod)
            return this
        if ((assetResSet?.size ?: 0) == 0)
            Thread {
                initResourceNoneRecursion(dir)
            }.start()
        return this
    }

    fun clear() {
        cleared = true
        assetResSet?.clear()
    }

    private fun addAssetsFile(f: String) {
        var file = f
        val flag = dir + File.separator
        if (!TextUtils.isEmpty(dir)) {
            val pos = file.indexOf(flag)
            if (pos >= 0) {
                file = file.substring(pos + flag.length)
            }
        }
        assetResSet?.add(file)
    }

    private fun initResourceNoneRecursion(dir: String): AssetsLoader {
        try {
            val list = LinkedList<String>()
            val resData = context?.assets?.list(dir) ?: emptyArray()
            for (res in resData) {
                val sub = dir + File.separator + res
                val tmp = context?.assets?.list(sub) ?: emptyArray()
                if (tmp.isEmpty()) {
                    addAssetsFile(sub)
                } else {
                    list.add(sub)
                }
            }
            while (!list.isEmpty()) {
                if (cleared) break
                val last = list.removeFirst()
                val tmp = context?.assets?.list(last) ?: emptyArray()
                if (tmp.isEmpty()) {
                    addAssetsFile(last)
                } else {
                    for (sub in tmp) {
                        val tmp1 =
                            context?.assets?.list(last + File.separator + sub) ?: emptyArray()
                        if (tmp1.isEmpty()) {
                            addAssetsFile(last + File.separator + sub)
                        } else {
                            list.add(last + File.separator + sub)
                        }
                    }
                }
            }
        } catch (e: IOException) {
            Timber.e(e)
        }
        return this
    }

    private fun getAssetFileStream(path: String): InputStream? {
        try {
            return context?.assets?.open(path)
        } catch (e: IOException) {
            Timber.e(e)
        }
        return null
    }

    companion object {

        val instance: AssetsLoader by lazy {
            AssetsLoader()
        }

    }

}