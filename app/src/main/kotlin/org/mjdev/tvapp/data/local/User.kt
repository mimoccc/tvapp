/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.data.local

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
@JsonClass(generateAdapter = true)
class User {

    @Id
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

}