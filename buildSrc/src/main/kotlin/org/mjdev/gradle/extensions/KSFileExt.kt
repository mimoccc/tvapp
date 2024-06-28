/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.extensions

import com.google.devtools.ksp.symbol.KSFile
import java.io.File

val KSFile.projectDir: File
    get() = File(filePath).let {
        var ff = it
        while (ff.name != "src") ff = ff.parentFile
        ff.parentFile
    }
