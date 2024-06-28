/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        @Suppress("JcenterRepositoryObsolete", "DEPRECATION")
        jcenter()
        maven(url = "https://www.jitpack.io")
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://mvn.dailymotion.com/repository/releases/")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven(url = "https://dl.bintray.com/kotlin/ktor")
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        @Suppress("JcenterRepositoryObsolete", "DEPRECATION")
        jcenter()
        maven(url = "https://www.jitpack.io")
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://mvn.dailymotion.com/repository/releases/")
        maven(url = "https://dl.bintray.com/kotlin/ktor")
    }
}

fun Settings.includeAllModules() {
    rootDir.walk().maxDepth(1).filter { file ->
        val isBuildSrc = file.name == "buildSrc"
        val isDirectory = file.isDirectory
        val hasBuildScriptFile = file.resolve("build.gradle.kts").exists()
        !isBuildSrc && isDirectory && hasBuildScriptFile
    }.forEach {
        include(":${it.name}")
    }
}

rootProject.name = "TvApp"
includeAllModules()
