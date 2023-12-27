/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.database.convert

import io.objectbox.converter.PropertyConverter

@Suppress("unused")
open class PropertyEnumConverter<T, R>(

    private val values: Array<T>,
    private val default: T,
    private val predicate: T.(R) -> Boolean,
    private val supplier: (T) -> R?

) : PropertyConverter<T, R> {

    override fun convertToEntityProperty(databaseValue: R?): T? =
        if (databaseValue == null) null
        else values.firstOrNull { p -> p.predicate(databaseValue) } ?: default

    override fun convertToDatabaseValue(entityProperty: T?): R? =
        if (entityProperty == null) null
        else supplier(entityProperty)

}