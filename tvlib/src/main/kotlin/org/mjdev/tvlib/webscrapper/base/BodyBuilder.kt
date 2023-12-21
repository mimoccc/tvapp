/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.webscrapper.base

import org.intellij.lang.annotations.Language

class BodyBuilder {
    var contentType: String = "text/plain"
    var data: String = ""

    fun json(@Language("JSON") json: String) {
        contentType = JSON
        data = json
    }

    fun xml(@Language("XML") xml: String) {
        contentType = XML
        data = xml
    }

    @Suppress("unused")
    fun form(form: String) {
        contentType = FORM
        data = form
    }

    fun json(init: Json.() -> Unit) {
        Json().also(init).toBody()
    }

    @Suppress("unused")
    fun form(init: Form.() -> Unit) {
        Form().also(init).toBody()
    }

    private fun Json.toBody() {
        this@BodyBuilder.contentType = JSON
        this@BodyBuilder.data = toString()
    }

    private fun Form.toBody() {
        this@BodyBuilder.contentType = FORM
        this@BodyBuilder.data = toString()
    }
}

class Json {
    private val elements = mutableListOf<Pair<String, String>>()

    @Suppress("MemberNameEqualsClassName")
    fun json(init: Json.() -> Unit): Json = Json().also(init)

    infix fun String.to(list: List<*>) {
        val value = list.joinToString(separator = ",", prefix = "[", postfix = "]") {
            when (it) {
                null -> "null"
                is Number, is Json, is Boolean -> it.toString()
                else -> """"$it""""
            }
        }
        elements += Pair(this, value)
    }

    infix fun String.to(string: String?) {
        elements += if (string == null) Pair(this, "null") else Pair(this, """"$string"""")
    }

    infix fun String.to(number: Number?) {
        elements += Pair(this, number.toString())
    }

    infix fun String.to(json: Json?) {
        elements += Pair(this, json.toString())
    }

    infix fun String.to(boolean: Boolean?) {
        elements += Pair(this, boolean.toString())
    }

    override fun toString(): String =
        elements.joinToString(separator = ",", prefix = "{", postfix = "}") { (key, value) ->
            """"$key":$value"""
        }
}

class Form {
    private val elements = mutableListOf<Pair<String, String>>()

    infix fun String.to(string: String?) {
        elements += if (string == null) Pair(this, "null") else Pair(this, """$string""")
    }

    infix fun String.to(number: Number?) {
        elements += Pair(this, number.toString())
    }

    infix fun String.to(boolean: Boolean?) {
        elements += Pair(this, boolean.toString())
    }

    override fun toString(): String =
        elements.joinToString(separator = "&") { (key, value) ->
            """$key=$value"""
        }
}

private const val JSON = "application/json"
private const val XML = "text/xml"
private const val FORM = "application/x-www-form-urlencoded"
