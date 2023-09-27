package org.mjdev.tvlib.assets

import android.content.Context
import android.net.Uri
import androidx.annotation.Keep
import java.io.File
import java.io.InputStream

@Keep
class AssetFile(
    val context: Context,
    private val assetsPath: String
) : InputStream(), IAssetObject {

    private val innerStream: InputStream by lazy {
        context.assets.open(assetsPath)
    }

    val name = assetsPath.let {
        File(Uri.parse(it).lastPathSegment ?: "").nameWithoutExtension
    }

    val file : File get() = File("file:///android_asset/$assetsPath")

    override fun read(): Int {
        return innerStream.read()
    }

    override val type: AssetType get() = AssetType.fromFileExt(assetsPath)

    override val size: Int get() = 0

    override fun contains(element: IAssetObject): Boolean {
        return false
    }

    override fun containsAll(elements: Collection<IAssetObject>): Boolean {
        return false
    }

    override fun isEmpty(): Boolean {
        return true
    }

    override fun iterator(): Iterator<IAssetObject> {
        return emptyList<IAssetObject>().iterator()
    }

    override fun toString(): String = assetsPath

}