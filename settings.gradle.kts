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

rootProject.name = "TvApp"

include(":annotations")
include(":processor")

include(":app")
include(":tvlib")
