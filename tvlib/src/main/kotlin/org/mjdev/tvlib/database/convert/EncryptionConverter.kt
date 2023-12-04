///*
// *  Copyright (c) Milan Jurkulák 2023.
// *  Contact:
// *  e: mimoccc@gmail.com
// *  e: mj@mjdev.org
// *  w: https://mjdev.org
// */
//
//@file:Suppress("unused")
//
//package org.mjdev.tvlib.database.convert
//
//import com.scottyab.aescrypt.AESCrypt
//import io.objectbox.converter.PropertyConverter
//
//class EncryptionConverter : PropertyConverter<String, String> {
//
//    companion object {
//
//        const val AES_KEY = "simply key to encrypt data"
//
//    }
//
//    override fun convertToDatabaseValue(entityProperty: String): String =
//        AESCrypt.encrypt(AES_KEY, entityProperty)
//
//    override fun convertToEntityProperty(databaseValue: String?): String =
//        AESCrypt.decrypt(AES_KEY, databaseValue)
//
//}