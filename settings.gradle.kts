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
        maven(url = "https://www.jitpack.io")
        @Suppress("JcenterRepositoryObsolete", "DEPRECATION")
        jcenter()
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://mvn.dailymotion.com/repository/releases/")
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

plugins {
    id("com.gradle.enterprise") version ("3.13.3")
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven(url = "https://www.jitpack.io")
        @Suppress("JcenterRepositoryObsolete", "DEPRECATION")
        jcenter()
        maven(url = "https://plugins.gradle.org/m2/")
        maven(url = "https://mvn.dailymotion.com/repository/releases/")
    }
}

gradleEnterprise {
    if (System.getenv("CI") != null) {
        buildScan {
            publishAlways()
            termsOfServiceUrl = "https://gradle.com/terms-of-service"
            termsOfServiceAgree = "yes"
        }
    }
}

rootProject.name = "TvApp"
include(":app")
include(":tvlib")
