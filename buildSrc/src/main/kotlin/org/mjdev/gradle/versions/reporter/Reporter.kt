/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.reporter

import org.mjdev.gradle.versions.result.Result
import java.io.OutputStream

interface Reporter {
    fun write(printStream: OutputStream, result: Result)

    fun getFileExtension(): String
}
