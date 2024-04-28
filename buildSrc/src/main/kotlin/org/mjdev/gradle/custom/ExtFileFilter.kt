/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.custom

import java.io.File
import java.io.FilenameFilter

@Suppress("unused")
class ExtFileFilter(extension: String?) : FilenameFilter {
    private var extension: String = String.format(".%s", extension)

    override fun accept(dir: File?, fileName: String?): Boolean {
        return fileName?.endsWith(extension) ?: false
    }
}