/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.extensions

import com.google.devtools.ksp.symbol.KSClassDeclaration

fun KSClassDeclaration.extractKeyName(): String? = primaryConstructor
    ?.parameters
    ?.first()
    ?.type
    ?.resolve()
    ?.declaration
    ?.simpleName?.asString()
