package org.mjdev.tvlib.assets

import android.content.Context
import android.content.res.AssetManager

@Suppress("unused")
class AssetDirectory(
    val context: Context,
    val path: String
) : IAssetObject {

    val assets: AssetManager = context.assets

    private val elements: HashMap<String, IAssetObject> = HashMap()

    override val type: AssetType get() = AssetType.DIRECTORY

    init {
        assets.list(path)?.forEach { name ->
            if ((assets.list("$path/$name")?.size ?: 0) > 0) {
                elements[name] = AssetDirectory(context, "$path/$name")
            } else {
                elements[name] = AssetFile(context, "$path/$name")
            }
        }
    }

    operator fun get(s: String): IAssetObject {
        return try {
            if (elements.containsKey(s))
                elements[s] ?: IAssetObject.EMPTY
            else
                IAssetObject.EMPTY
        } catch (e: Exception) {
            IAssetObject.EMPTY
        }
    }

    override val size: Int get() = elements.size

    override fun contains(element: IAssetObject): Boolean = elements.containsValue(element)

    override fun containsAll(elements: Collection<IAssetObject>): Boolean =
        elements.containsAll(elements)

    fun contains(name: String) = elements.containsKey(name)

    override fun isEmpty(): Boolean = (size == 0)

    override fun iterator(): Iterator<IAssetObject> = elements.values.iterator()

    override fun toString(): String {
        return "[$path]"
    }

}