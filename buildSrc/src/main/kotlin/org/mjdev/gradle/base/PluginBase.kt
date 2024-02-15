/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.base

import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.TestedExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.mjdev.gradle.extensions.extension

@Suppress("MemberVisibilityCanBePrivate", "DEPRECATION")
open class PluginBase<E : PluginExtension, F : TestedExtension>(
    val extensionClass: Class<E>,
    val init: PluginScope<E, F>.() -> Unit
) : Plugin<Project> {

    lateinit var scope: PluginScope<E, F>
    lateinit var project: Project

    val extensionName :String
        get() = extensionClass.newInstance().name

    @Suppress("UNCHECKED_CAST")
    val params: E
        get() = getExtension(extensionName, extensionClass) as E

    override fun apply(project: Project) {
        this.project = project
        createExtension(extensionName, extensionClass)
        scope = PluginScope(this@PluginBase)
    }

    val androidComponents: AndroidComponentsExtension<*, *, *>
        get() = project.extensions.getByType(AndroidComponentsExtension::class.java)

    fun createExtension(name: String, cls: Class<*>) =
        project.extension(name, cls, true)


    fun getExtension(name: String, cls: Class<*>) =
        project.extension(name, cls, false)
}
