/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.reporter

import org.gradle.api.Project
import java.io.OutputStream
import java.io.PrintStream

abstract class AbstractReporter(
    open val project: Project,
    open val revision: String,
    open val gradleReleaseChannel: String,
) : Reporter

fun OutputStream.print(s: String = "") {
    (this as PrintStream).print(s)
}

fun OutputStream.println(s: String = "") {
    (this as PrintStream).println(s)
}
