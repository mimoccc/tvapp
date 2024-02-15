/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.versions.base

import org.mjdev.gradle.versions.reporter.Reporter
import org.mjdev.gradle.versions.result.Result
import org.gradle.api.Action

sealed interface OutputFormatterArgument {

    class BuiltIn(val formatterNames: String) : OutputFormatterArgument

    class CustomReporter(val reporter: Reporter) : OutputFormatterArgument

    class CustomAction(val action: Action<Result>) : OutputFormatterArgument

    companion object {
        val DEFAULT = BuiltIn(formatterNames = "text")
    }

}
