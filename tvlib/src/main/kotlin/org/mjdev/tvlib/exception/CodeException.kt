/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.exception

class CodeException(
    message: String = "Unhandled Exception.",
    code: Int = -1,
) : Exception(String.format("$code : $message."))