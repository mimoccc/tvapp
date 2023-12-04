/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

//package org.mjdev.tvlib.database.convert
//
//import com.squareup.moshi.Moshi
//import io.objectbox.converter.PropertyConverter
//
//@Suppress("unused")
//open class PropertyJsonConverter<T : Any>(
//
//    private val cls: Class<T>
//
//    ) : PropertyConverter<T, String> {
//
//    override fun convertToEntityProperty(databaseValue: String?): T? =
//        if (databaseValue == null) null
//        else Moshi.Builder().build().adapter(cls).fromJson(databaseValue) as T
//
//    override fun convertToDatabaseValue(entityProperty: T?): String? =
//        if (entityProperty == null) null
//        else Moshi.Builder().build().adapter(cls).toJson(entityProperty)
//
//}