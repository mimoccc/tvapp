package org.mjdev.tvlib.assets

import java.io.File
import kotlin.reflect.KClass

enum class AssetType(
    val fileExt: String,
    val parserClass: KClass<*>
) {

    UNKNOWN("", Unit::class),
    DIRECTORY("", Unit::class);
//    M3UFile("m3u", M3UParser::class),
//    WEBSOURCE("json", JSONDescFile::class);

    companion object {

        fun fromFileExt(fileName: String): AssetType {
            return File(fileName).let { file ->
                when (file.extension) {
//                    "json" -> WEBSOURCE
//                    "m3u" -> M3UFile
//                    "m3u8" -> M3UFile
                    else -> UNKNOWN
                }
            }
        }

    }

}