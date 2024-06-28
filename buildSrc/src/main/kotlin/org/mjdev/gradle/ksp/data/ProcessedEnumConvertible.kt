/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.ksp.data

import com.google.devtools.ksp.symbol.KSClassDeclaration

data class ProcessedEnumConvertible(
    val packageName: String,
    val className: String,
    val keyName: String?,
    val classDeclaration: KSClassDeclaration
)