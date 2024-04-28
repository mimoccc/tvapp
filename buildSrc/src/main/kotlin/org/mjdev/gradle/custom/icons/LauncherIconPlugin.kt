//package org.mjdev.gradle.icons
//
//import com.android.build.gradle.api.ApplicationVariant
//import org.gradle.api.NamedDomainObjectContainer
//import org.gradle.api.Plugin
//import org.gradle.api.Project
//import org.gradle.api.Task
//import org.mjdev.gradle.extensions.androidExtension
//import org.mjdev.gradle.extensions.buildDirectory
//import org.mjdev.gradle.extensions.extension
//import org.mjdev.gradle.icons.filter.LauncherIconFilter
//import java.io.File
//
//@Suppress("DefaultLocale")
//class LauncherIconPlugin : Plugin<Project> {
//    companion object {
//        const val PLUGIN_NAME = "launcherIcon"
//        const val RIBBON_VARIANTS = "variants"
//        const val RIBBON_BUILD_TYPES = "buildTypes"
//        const val RIBBON_PRODUCT_FLAVORS = "productFlavors"
//    }
//
//    fun getGeneratedResDir(project: Project, variant: ApplicationVariant): File {
//        return File(project.buildDirectory, "generated/$PLUGIN_NAME/res/${variant.name}")
//    }
//
//    @Override
//    override fun apply(project: Project) {
//        project.createExtension<LauncherIconExtension>()
//        val ribbonVariants: NamedDomainObjectContainer<LauncherIconConfig> =
//                project.container(LauncherIconConfig::class.java)
//        ribbonVariants.let {
//            project.extensions.add(RIBBON_VARIANTS, it)
//        }
//        val ribbonBuildTypes: NamedDomainObjectContainer<LauncherIconConfig> =
//                project.container(LauncherIconConfig::class.java)
//        ribbonBuildTypes.let {
//            project.extensions.add(RIBBON_BUILD_TYPES, it)
//        }
//        val ribbonProductFlavors: NamedDomainObjectContainer<LauncherIconConfig> =
//                project.container(LauncherIconConfig::class.java)
//        ribbonProductFlavors.let {
//            project.extensions.add(RIBBON_PRODUCT_FLAVORS, it)
//        }
//        project.afterEvaluate {
//            project.androidExtension.let { app ->
//                val extension = project.extension(LauncherIconExtension::class)
//                val tasks = ArrayList<Task>()
//                app.applicationVariants.all { variant ->
//                    val configs = mutableListOf<LauncherIconConfig>()
//                    ribbonVariants.forEach { econfig ->
//                        if (variant.name == econfig.name)
//                            configs.add(econfig)
//                    }
//                    if (configs.isEmpty()) {
//                        ribbonProductFlavors.forEach { econfig ->
//                            if (variant.flavorName == econfig.name)
//                                configs.add(econfig)
//                        }
//                        ribbonBuildTypes.forEach { econfig ->
//                            if (variant.buildType.name == econfig.name)
//                                configs.add(econfig)
//                        }
//                    }
//                    var enabled = true
//                    configs.forEach { econfig ->
//                        enabled = enabled && econfig.enabled
//                    }
//                    if (enabled) {
//                        val filters = ArrayList<LauncherIconFilter>()
//                        configs.forEach { econfig ->
//                            filters.addAll(econfig.filters)
//                        }
//                        if (filters.isEmpty() && variant.buildType.isDebuggable) {
//                            val ribbonText = if (extension?.isDefaultFlavorNaming == true)
//                                variant.flavorName
//                            else
//                                variant.buildType.name
//                            filters.add(LauncherIconConfig(ribbonText).greenRibbonFilter())
//                        }
//                        val generatedResDir = getGeneratedResDir(project, variant)
//                        app.sourceSets.findByName(variant.name)?.res?.srcDir(generatedResDir)
//                        project.createTask<EasyLauncherTask>(
//                                "$PLUGIN_NAME${variant.name.capitalize()}"
//                        ) { task ->
//                            task.let {
//                                it.setTaskDescription("Generate icon due application variant")
//                                it.variant = variant
//                                it.outputDir = generatedResDir
//                                it.iconNames = extension?.getIconNames() ?: emptySet()
//                                it.foregroundIconNames = extension?.getForegroundIconNames()
//                                        ?: emptySet()
//                                it.filters = filters
//                                tasks.add(it)
//                                project.getTasksByName(
//                                        "generate${variant.name.capitalize()}Resources",
//                                        false
//                                ).forEach { t ->
//                                    t.dependsOn(task)
//                                }
//                            }
//                        }
//                    }
//                }
//                project.task(PLUGIN_NAME).dependsOn(tasks)
//            } ?: throw Exception(
//                    "Not an Android application; did you forget " +
//                            "use `apply plugin: 'com.android.application`?"
//            )
//        }
//    }
//}
