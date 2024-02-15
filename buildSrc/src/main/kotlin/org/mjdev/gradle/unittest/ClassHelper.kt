/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.unittest

import java.lang.reflect.Method

internal interface ClassHelper {
    fun getTestObjectMap(clazz: Class<*>): Map<String, Map<String, Class<*>>>
    fun getMethodMap(clazz: Class<*>): Pair<Int, Map<String, Method>>
}