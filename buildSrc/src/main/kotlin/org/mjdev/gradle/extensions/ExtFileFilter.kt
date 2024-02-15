/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.extensions

import java.io.File
import java.io.FilenameFilter

class ExtFileFilter(extension: String?) : FilenameFilter {
    var extension: String = String.format(".%s", extension)

    override fun accept(dir: File?, fileName: String?): Boolean {
        return fileName?.endsWith(extension) ?: false
    }
}