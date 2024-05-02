/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("unused", "DEPRECATION", "RedundantRunCatching")

package org.mjdev.gradle.extensions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariant
import io.gitlab.arturbosch.detekt.Detekt
import org.gradle.TaskExecutionRequest
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.plugins.ExtraPropertiesExtension
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.SourceSet.MAIN_SOURCE_SET_NAME
import org.gradle.api.tasks.SourceSet.TEST_SOURCE_SET_NAME
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.internal.impldep.jakarta.xml.bind.DatatypeConverter.parseBoolean
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.the
import org.gradle.kotlin.dsl.withType
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.mjdev.gradle.tasks.CreatePropsTask
import org.mjdev.gradle.tasks.CleanProjectTask
import org.kordamp.gradle.plugin.markdown.tasks.MarkdownToHtmlTask
import org.mjdev.gradle.tasks.ReleaseNotesCreateTask
import org.mjdev.gradle.tasks.WebServiceCreateTask
import org.mjdev.gradle.tasks.ZipReleaseCreateTask
import java.io.File
import java.io.FileInputStream
import java.util.Properties
import kotlin.reflect.KClass

private const val VERSION_TAG_MAJOR = "majorVersion"
private const val VERSION_TAG_MINOR = "minorVersion"
private const val VERSION_TAG_PATCH = "patchVersion"

val Project.versionCode
    get(): Int {
        val major = runCatching {
            rootProject.extra.get(VERSION_TAG_MAJOR)?.toString()?.toInt()
        }.getOrNull() ?: 1
        val minor = runCatching {
            rootProject.extra.get(VERSION_TAG_MINOR)?.toString()?.toInt()
        }.getOrNull() ?: 0
        val patch = runCatching {
            rootProject.extra.get(VERSION_TAG_PATCH)?.toString()?.toInt()
        }.getOrNull() ?: 0
        return major * 10000 + minor * 100 + patch
    }

val Project.versionName: String
    get() {
        val major = runCatching {
            rootProject.extra.get(VERSION_TAG_MAJOR)?.toString()?.toInt()
        }.getOrNull() ?: 1
        val minor = runCatching {
            rootProject.extra.get(VERSION_TAG_MINOR)?.toString()?.toInt()
        }.getOrNull() ?: 0
        val patch = runCatching {
            rootProject.extra.get(VERSION_TAG_PATCH)?.toString()?.toInt()
        }.getOrNull() ?: 0
        return "${major}.${minor}.${patch}"
    }

val Project.taskRequest: List<TaskExecutionRequest>
    get() = gradle.startParameter.taskRequests

val Project.taskRequestsArgs: List<String>
    get() = ArrayList<String>().also { argsList ->
        taskRequest.map { tr ->
            tr.args
        }.let { list ->
            list.forEach { argsList.addAll(it) }
        }
    }

val Project.taskRequestAssembleTasks
    get() = taskRequestsArgs.filter { arg -> arg.startsWith("assemble") }

val Project.taskRequestBundleTasks
    get() = taskRequestsArgs.filter { arg -> arg.startsWith("bundle") }

val Project.taskRequestHasAssembles
    get() = taskRequestAssembleTasks.isNotEmpty()

val Project.taskRequestHasBundles
    get() = taskRequestBundleTasks.isNotEmpty()

//val Project.currentFlavor: String
//    get() {
//        val buildTypes = arrayOf("Release", "Debug", "Mock")
//        val str: String = when {
//            taskRequestHasAssembles -> taskRequestAssembleTasks.joinToString { "," }
//            taskRequestHasBundles -> taskRequestBundleTasks.joinToString { "," }
//            else -> ""
//        }
//        return when {
//            taskRequestHasAssembles -> Pattern
//                .compile("assemble(\\w+)(${buildTypes.joinToString { "|" }})")
//                .matcher(str)
//                .let { matcher ->
//                    if (matcher.find()) matcher.group(1).lowercase()
//                    else ""
//                }
//
//            taskRequestHasBundles -> Pattern
//                .compile("bundle(\\w+)(${buildTypes.joinToString { "|" }})")
//                .matcher(str)
//                .let { matcher ->
//                    if (matcher.find()) matcher.group(1).lowercase()
//                    else ""
//                }
//
//            else -> "-"
//        }
//    }

val Project.android: ApplicationExtension
    get() = extensions.getByType(ApplicationExtension::class.java)

val Project.isAndroid: Boolean
    get() = extensions.findByType<ApplicationExtension>() != null

val Project.packageName: String
    get() = applicationId

val Project.androidStudioVersion
    get() = project.extra.properties["android.studio.version"]

val Project.runningFromAndroidStudio
    get() = parseBoolean(project.extra.properties["android.injected.invoked.from.ide"].toString())

val Project.isAndroidStudio
    get() = project.extra.properties.keys.contains("android.studio.version")

val Project.applicationId: String
    get() = if (isAndroid) android.defaultConfig.applicationId ?: "" else ""

val Project.androidComponents: AndroidComponentsExtension<*, *, *>
    get() = extensions.getByType(AndroidComponentsExtension::class.java)

val Project.buildDirectory: File
    get() = layout.buildDirectory.asFile.get()

val Project.sourceSets
    get() = properties["sourceSets"] as SourceSetContainer

val Project.mainSourceSet: SourceSet?
    get() = sourceSets.findByName(MAIN_SOURCE_SET_NAME)

val Project.testSourceSet: SourceSet?
    get() = sourceSets.findByName(TEST_SOURCE_SET_NAME)

val Project.androidExtension: AppExtension
    get() = extensions.getByType(AppExtension::class.java)

val Project.kotlinExtension: KotlinProjectExtension
    get() = extensions.getByName("kotlin") as KotlinProjectExtension

val Project.variants: List<ApplicationVariant>
    get() = androidExtension.applicationVariants.toList()

val Project.unitTestDir: File
    get() = file("src").file("test").file("java")

val Project.assembleTasks
    get() = tasks.filter { t -> t.name.startsWith("assemble") }

val Project.libs
    get() = the<LibrariesForLibs>()

val Project.log: Logger
    get() = Logging.getLogger(this::class.java)

//inline fun <reified T : Task> Project.registerTask(
//    name: String,
//    configurationAction: Action<T> = Action<T> {}
//): List<T> = if (T::class.isSubclassOf(BaseTask::class)) {
//    mutableListOf<T>().apply {
//        variants.forEach { variant ->
//            registerTask<T>(
//                "$name${variant.name.capitalize()}"
//            ).apply {
//                (this.get() as BaseTask).variant = variant
//            }.get().also { task ->
//                task.apply {
//                    configurationAction.execute(this)
//                    add(this)
//                }
//            }
//        }
//    }
//} else {
//    listOf(registerTask<T>(name).get().apply {
//        configurationAction.execute(this)
//    })
//}

fun Project.loadRootPropertiesFile(
    path: String,
    exposeToExtra: Boolean = true
) = loadPropertiesFile(
    project.rootDir.resolve(path),
    if (exposeToExtra) project.rootProject.extra else null
)

fun Project.loadBuildPropertiesFile(
    path: String,
    exposeToExtra: Boolean = true
) = loadPropertiesFile(
    project.buildDir.resolve(path),
    if (exposeToExtra) project.extra else null
)

@Suppress("UnusedReceiverParameter")
fun Project.loadPropertiesFile(
    propertiesFile: File,
    exposeToExtra: ExtraPropertiesExtension?
) = Properties().apply {
    try {
        load(FileInputStream(propertiesFile))
    } catch (e: Exception) {
        // ignored
    }
}.apply {
    if (exposeToExtra != null) {
        forEach { prop ->
            exposeToExtra.set(prop.key.toString(), prop.value.toString())
        }
    }
}

inline fun <reified T> Project.extension(
    name: String? = null,
    config: T.() -> Unit = {}
): T = runCatching {
    var cfg: T? = project.extensions.findByType(T::class.java)
    if (cfg == null && name != null) {
        project.extensions.create(name, T::class.java)
    }
    cfg = project.extensions.findByType(T::class.java)
    cfg!!.apply {
        config(this)
    }
}.getOrThrow()

inline fun <reified T : Any> Project.extension(): T =
    rootProject.extensions.findByType(T::class.java)
        ?: project.extensions.findByType(T::class.java)
        ?: throw (GradleException("Extension not configured : ${T::class.java.simpleName}"))

fun <T> Project.extension(cls: Class<T>): T =
    rootProject.extensions.findByType(cls)
        ?: project.extensions.findByType(cls)
        ?: throw (GradleException("Extension not configured : ${cls.simpleName}"))

inline fun <reified T : Task> Project.registerTask(
    name: String? = null,
    fn: T.() -> Unit = {}
): T {
    val taskName = name ?: T::class.java.simpleName
            .replace("Task", "")
            .unCapitalize()
    tasks.register(taskName, T::class.java)
    return tasks.findByName(taskName).let { task ->
        task as T
    }.apply {
        fn(this)
    }
}

fun <T : Plugin<*>> Project.apply(type: KClass<T>): T = plugins.apply(type.java)

fun Project.apply(id: String): Plugin<*> = plugins.apply(id)

inline fun <reified T : Task> Project.task(scoped: T.() -> Unit = {}): T {
    val task = tasks.withType<T>().first()
    scoped(task)
    return task
}

inline fun <reified T : Task> Project.task(
    name: String,
    scoped: T.() -> Unit = {}
): T {
    val task = tasks.named(name).get() as T
    scoped(task)
    return task
}

fun Project.kotlinCompileOptions(scoped: KotlinCompile.() -> Unit = {}) =
    task<KotlinCompile>(scoped)

fun Project.cleanTask(scoped: Task.() -> Unit = {}) =
    task(name = "clean", scoped = scoped)

fun Project.cleanProjectTask(scoped: CleanProjectTask.() -> Unit = {}) =
    task<CleanProjectTask>(scoped)

fun Project.detektTask(scoped: Detekt.() -> Unit = {}) =
    task<Detekt>(scoped)

fun Project.createPropsTask(scoped: CreatePropsTask.() -> Unit = {}) =
    task<CreatePropsTask>(scoped)

fun Project.webServiceCreateTask(scoped: WebServiceCreateTask.() -> Unit = {}) =
    task<WebServiceCreateTask>(scoped)

fun Project.releaseNotesCreateTask(scoped: ReleaseNotesCreateTask.() -> Unit = {}) =
    task<ReleaseNotesCreateTask>(scoped)

fun Project.zipReleaseCreateTask(scoped: ZipReleaseCreateTask.() -> Unit = {}) =
    task<ZipReleaseCreateTask>(scoped)

fun Project.dokkaTask(scoped: DokkaTask.() -> Unit) =
    task<DokkaTask>(scoped)

fun Project.markDownToHtmlTask(scoped: MarkdownToHtmlTask.() -> Unit) =
    task<MarkdownToHtmlTask>(scoped)

fun Project.registerSources(outDir: File, variant: BaseVariant) {
    if (outDir.absolutePath.contains("generated/source")) {
        project.kotlinExtension.sourceSets.findByName(variant.name)?.apply {
            kotlin.srcDirs(outDir)
        }
        project.androidExtension.sourceSets.findByName(variant.name)?.apply {
            kotlin.srcDirs(outDir)
        }
    } else if (outDir.absolutePath.contains("generated/res")) {
        project.androidExtension.sourceSets.findByName(variant.name)?.apply {
            res.srcDirs(outDir)
        }
//        variant.mergeResourcesProvider.configure {
//            it.dependsOn(task)
//        }
    }
}

@Suppress("UNCHECKED_CAST")
fun <T> Project.extension(
    name: String,
    cls: Class<T>,
    canCreate: Boolean = true
): T? = extensions.findByName(name) as? T ?: if (canCreate) extensions.create(
    name,
    cls
) else null


fun Project.propOrDefault(propertyName: String, defaultValue: Any): Any = project
    .properties[propertyName] ?: (System.getenv(propertyName) ?: defaultValue)

fun Project.propertyOrEmpty(name: String): String = findProperty(name) as String? ?: ""

fun Project.fileTree(directory: File, inc: String, exc: String): List<File> {
    return project.fileTree(directory) {
        setExcludes(listOf(exc))
        setIncludes(listOf(inc))
    }.toList()
}

fun Project.fileTree(directory: String, inc: String, exc: String): List<File> {
    return project.fileTree(directory) {
        setExcludes(listOf(exc))
        setIncludes(listOf(inc))
    }.toList()
}

fun Project.androidComponents(
    block: AndroidComponentsExtension<*, *, *>.() -> Unit
) {
    block.invoke(androidComponents)
}
