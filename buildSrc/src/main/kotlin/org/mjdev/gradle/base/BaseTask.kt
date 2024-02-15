/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("DEPRECATION")

package org.mjdev.gradle.base

import com.android.build.gradle.api.BaseVariant
import org.gradle.api.DefaultTask
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.Internal
import org.mjdev.gradle.extensions.capitalize

open class BaseTask : DefaultTask() {

    private val log by lazy {
        Logging.getLogger(this::class.java)
    }

    @Internal
    lateinit var variant: BaseVariant

    val variantName: String
        @Internal
        get() = variant.name.capitalize()

    fun println(message: String) = log.lifecycle(message)

}