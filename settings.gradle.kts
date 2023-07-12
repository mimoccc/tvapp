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
        maven(url = "https://www.jitpack.io")
        @Suppress("JcenterRepositoryObsolete", "DEPRECATION")
        jcenter()
        maven(url = "https://plugins.gradle.org/m2/")
    }
}

plugins {
    id("com.gradle.enterprise") version ("3.13.3")
}

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