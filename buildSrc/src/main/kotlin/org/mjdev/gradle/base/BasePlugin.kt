/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.gradle.base

//import com.akuleshov7.ktoml.Toml
import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.internal.catalog.VersionCatalogView
import org.gradle.internal.impldep.org.tomlj.Toml
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.withType
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.FileReader
import kotlin.reflect.KClass

@Suppress("HasPlatformType", "MemberVisibilityCanBePrivate")
abstract class BasePlugin : Plugin<Project> {

    val IMPLEMENTATION = "implementation"

    override fun apply(project: Project) {
        project.work()
    }

    abstract fun Project.work()

    inline fun <reified T> Project.extension(name: String? = null): T = run {
        var cfg = project.extensions.findByType(T::class.java)
        if (cfg == null && name != null) {
            project.extensions.add(name, T::class.java)
        }
        cfg = project.extensions.findByType(T::class.java)
        cfg!!
    }

    inline fun <reified T : Any> Project.extension(): T = runCatching {
        extensions.getByType(T::class.java)
    }.onFailure {
        println("Config missing.")
    }.getOrThrow()

    fun <T : Plugin<*>> Project.apply(type: KClass<T>): T = plugins.apply(type.java)

    fun Project.apply(id: String) = plugins.apply(id)

    fun Project.kotlinCompileOptions(scoped: KotlinCompile.() -> Unit) {
        tasks.withType<KotlinCompile>().first().also { t -> scoped(t) }
    }

    fun Project.detektTask(scoped: Detekt.() -> Unit) {
        tasks.withType<Detekt>().first().also { t -> scoped(t) }
    }

    fun Project.dokkaTask(scoped: DokkaTask.() -> Unit) {
        tasks.withType<DokkaTask>().first().also { t -> scoped(t) }
    }

    val Project.assembleTasks
        get() = tasks.filter { t -> t.name.startsWith("assemble") }

    fun Task.runAfterAssembleTask() {
        project.assembleTasks.forEach { t -> t.finalizedBy(this) }
    }

    val Project.versions
        get() = extra["versions"]

    val Project.libraries
        get() = extra["libraries"]

    val Project.plugins
        get() = extra["plugins"]

    fun Project.loadVersionCatalog(path: String) {
        val file = rootDir.resolve(path)
        println("Parsing: $file")
        val toml = FileReader(file).use { f ->
            Toml.parse(f)
        }
        val versions = toml.getTable("versions")?.toMap()
        val libraries = toml.getTable("libraries")
        val plugins = toml.getTable("libraries")
        extra["versions"] = versions
        extra["libraries"] = libraries
        extra["plugins"] = plugins
    }

}