/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.data.local

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//@Entity
@JsonClass(generateAdapter = true)
class User {

//    @Id
    @Json(name = "id")
    var id: Long = 0

    @Json(name = "sub")
    var sub: String? = ""

    @Json(name = "given_name")
    var givenName: String? = ""

    @Json(name = "family_name")
    var familyName: String? = ""

    @Json(name = "nickname")
    var nickname: String? = ""

    @Json(name = "name")
    var fullName: String? = ""

    @Json(name = "picture")
    var pictureUrl: String? = ""

    @Json(name = "locale")
    var locale: String? = ""

    @Json(name = "updated_at")
    var lastUpdated: String? = ""

    @Json(name = "pin_code", ignore = true)
    var pinCode: String = ""

    @Json(name = "settings_json", ignore = true)
    var settingsJson: String = ""

    @Json(name = "age", ignore = true)
    var age: Int = -1

    @Json(name = "email", ignore = true)
    var email:String? = ""

    @Json(name = "emailVerified", ignore = true)
    var emailVerified : Boolean = false

    override fun equals(other: Any?): Boolean =
        if (other !is User) false else other.hashCode() == hashCode()

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + (sub?.hashCode() ?: 0)
        result = 31 * result + (givenName?.hashCode() ?: 0)
        result = 31 * result + (familyName?.hashCode() ?: 0)
        result = 31 * result + (nickname?.hashCode() ?: 0)
        result = 31 * result + (fullName?.hashCode() ?: 0)
        result = 31 * result + (pictureUrl?.hashCode() ?: 0)
        result = 31 * result + (locale?.hashCode() ?: 0)
        result = 31 * result + (lastUpdated?.hashCode() ?: 0)
        result = 31 * result + pinCode.hashCode()
        result = 31 * result + settingsJson.hashCode()
        result = 31 * result + age
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + emailVerified.hashCode()
        return result
    }
}