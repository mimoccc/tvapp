/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.database.convert

import io.objectbox.converter.PropertyConverter
import org.json.JSONArray

@Suppress("unused")
class StringListConverter : PropertyConverter<List<String?>?, String?> {

    override fun convertToEntityProperty(
        databaseValue: String?
    ): List<String?> = if (databaseValue == null) ArrayList()
    else try {
        val array = JSONArray(databaseValue)
        val ret = ArrayList<String?>()
        for (i in 0 until array.length()) {
            ret.add(array.getString(i))
        }
        ret
    } catch (e: Exception) {
        ArrayList()
    }

    override fun convertToDatabaseValue(
        entityProperty: List<String?>?
    ): String? {
        return try {
            if (entityProperty == null) null else JSONArray(entityProperty).toString()
        } catch (e: Exception) {
            null
        }
    }

}