/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

plugins {
    id("LibPlugin")
}

libConfig {
    namespace = "org.mjdev.tvlib"
    description = "Smart TV android app lib for android applications running on any android device"

    autoCorrectCode = true
    createDocumentation = true
    minify = false
}
