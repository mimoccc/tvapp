@file:Suppress("UnstableApiUsage")

/*
* Copyright (c) Milan Jurkulák 2023.
* Contact:
* e: mimoccc@gmail.com
* e: mj@mjdev.org
* w: https://mjdev.org
*/

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TvApp"
include(":app")