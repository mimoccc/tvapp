package org.mjdev.tvlib.assets

interface IAssetObject : Collection<IAssetObject> {

    val type : AssetType

    companion object {

        val EMPTY = object: IAssetObject {
            override val type: AssetType = AssetType.UNKNOWN
            override val size: Int = 0

            override fun contains(element: IAssetObject): Boolean = false

            override fun containsAll(elements: Collection<IAssetObject>): Boolean =false

            override fun isEmpty(): Boolean = true

            override fun iterator(): Iterator<IAssetObject> = emptyList<IAssetObject>().iterator()
        }

    }

}