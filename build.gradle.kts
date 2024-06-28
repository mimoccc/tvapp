/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.gradle.ksp) apply false
    alias(libs.plugins.gradle.ktlint) apply false
    alias(libs.plugins.gradle.versions) apply true
    alias(libs.plugins.gradle.catalogs.update) apply true
    alias(libs.plugins.compose.screenshot) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://storage.googleapis.com/r8-releases/raw")
        maven("https://mvn.dailymotion.com/repository/releases/")
    }

    dependencies {
        classpath(libs.gradle)
        classpath(libs.gradle.api)
        classpath(libs.gradle.kotlin.plugin)
        classpath(libs.gradle.tools.r8)
        classpath(libs.gradle.detekt.plugin)
        classpath(libs.gradle.objectbox.plugin)
        classpath(libs.gradle.paparazzi.plugin)
        classpath(libs.gradle.kotlinter)
        classpath(libs.gradle.dokka.plugin)
        classpath(libs.gradle.markdown.plugin)
//        classpath(libs.gradle.ospackage.plugin)
    }

    configurations.classpath {
        resolutionStrategy.activateDependencyLocking()
    }
}

versionCatalogUpdate {
    sortByKey = true
    pin {
//        versions = ["my-version-name", "other-version"]
//        libraries = [libs.my.library.reference, libs.my.other.library.reference]
//        plugins = [libs.plugins.my.plugin, libs.plugins.my.other.plugin]
//        groups = ["com.somegroup", "com.someothergroup"]
    }
    keep {
//        versions = ["my-version-name", "other-version"]
//        libraries = [libs.my.library.reference, libs.my.other.library.reference]
//        plugins = [libs.plugins.my.plugin, libs.plugins.my.other.plugin]
//        groups = ["com.somegroup", "com.someothergroup"]
        keepUnusedVersions = false
        keepUnusedLibraries = false
        keepUnusedPlugins = false
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}

subprojects {
    pluginManager.withPlugin("java") {
        configure<JavaPluginExtension> {
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(17))
            }
        }

        tasks.withType<JavaCompile>().configureEach {
            options.release.set(8)
        }
    }

    pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
        tasks.withType<KotlinCompile>().configureEach {
            compilerOptions {
//                jvmTarget.set(17)
                progressiveMode.set(true)
            }
        }
    }

    if (JavaVersion.current() >= JavaVersion.VERSION_16) {
        tasks.withType<Test>().configureEach {
            jvmArgs(
                "--add-opens=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED",
                "--add-opens=jdk.compiler/com.sun.tools.javac.code=ALL-UNNAMED",
                "--add-opens=jdk.compiler/com.sun.tools.javac.comp=ALL-UNNAMED",
                "--add-opens=jdk.compiler/com.sun.tools.javac.file=ALL-UNNAMED",
                "--add-opens=jdk.compiler/com.sun.tools.javac.jvm=ALL-UNNAMED",
                "--add-opens=jdk.compiler/com.sun.tools.javac.main=ALL-UNNAMED",
                "--add-opens=jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED",
                "--add-opens=jdk.compiler/com.sun.tools.javac.processing=ALL-UNNAMED",
                "--add-opens=jdk.compiler/com.sun.tools.javac.tree=ALL-UNNAMED",
                "--add-opens=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED",
            )
        }
    }

//    pluginManager.withPlugin("com.vanniktech.maven.publish") {
//        apply(plugin = "org.jetbrains.dokka")
//
//        tasks.withType<DokkaTask>().configureEach {
//            outputDirectory.set(rootDir.resolve("../docs/0.x"))
//            dokkaSourceSets.configureEach {
//                skipDeprecated.set(true)
//            }
//        }

//        configure<MavenPublishBaseExtension> {
//            publishToMavenCentral(automaticRelease = true)
//            signAllPublications()
//        }
//    }

}
