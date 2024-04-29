/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@Suppress("DEPRECATION", "JcenterRepositoryObsolete")
repositories {
    jcenter()
    google()
    mavenCentral()
    gradlePluginPortal()
    maven(url = "https://www.jitpack.io")
    maven(url = "https://plugins.gradle.org/m2/")
    maven(url = "https://oss.sonatype.org/content/repositories/public")
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
}

plugins {
    `kotlin-dsl`
//    id("com.github.ben-manes.versions") version "0.41.0"
//    id("nl.littlerobots.version-catalog-update") version "0.8.4"
}

//versionCatalogUpdate {
//    // sort the catalog by key (default is true)
//    sortByKey = true
//    // Referenced that are pinned are not automatically updated.
//    // They are also not automatically kept however (use keep for that).
//    pin {
//        // pins all libraries and plugins using the given versions
//        versions = ["my-version-name", "other-version"]
//        // pins specific libraries that are in the version catalog
//        libraries = [libs.my.library.reference, libs.my.other.library.reference]
//        // pins specific plugins that are in the version catalog
//        plugins = [libs.plugins.my.plugin, libs.plugins.my.other.plugin]
//        // pins all libraries (not plugins) for the given groups
//        groups = ["com.somegroup", "com.someothergroup"]
//    }
//    keep {
//        // keep has the same options as pin to keep specific entries
//        // note that for versions it will ONLY keep the specified version, not all
//        // entries that reference it.
//        versions = ["my-version-name", "other-version"]
//        libraries = [libs.my.library.reference, libs.my.other.library.reference]
//        plugins = [libs.plugins.my.plugin, libs.plugins.my.other.plugin]
//        groups = ["com.somegroup", "com.someothergroup"]
//
//        // keep versions without any library or plugin reference
//        keepUnusedVersions = true
//        // keep all libraries that aren't used in the project
//        keepUnusedLibraries = true
//        // keep all plugins that aren't used in the project
//        keepUnusedPlugins = true
//    }
//}

//ospackage {
//    packageName = 'foo'
//    version = '1.2.3'
//    release = '1'
//    arch = I386
//    os = LINUX
//
//    installUtils file('scripts/rpm/utils.sh')
//    preInstall file('scripts/rpm/preInstall.sh')
//    postInstall file('scripts/rpm/postInstall.sh')
//    preUninstall 'touch /tmp/myfile'
//    postUninstall file('scripts/rpm/postUninstall.sh')
//
//    requires('qux')
//
//    into '/opt/foo'
//
//    from(jar.outputs.files) {
//        into 'lib'
//    }
//    from(configurations.runtime) {
//        into 'lib'
//    }
//    from('lib') {
//        into 'lib'
//    }
//    from('scripts') {
//        into 'bin'
//        exclude 'database'
//        fileMode = 0550
//    }
//    from('src/main/resources') {
//        fileType CONFIG | NOREPLACE
//                into 'conf'
//    }
//    from('home') {
//        // Creating directory entries (or not) in the RPM is normally left up to redline-rpm library.
//        // Use this to explicitly create an entry -- for setting directory fileMode on system directories.
//        createDirectoryEntry = true
//        fileMode = 0500
//        into 'home'
//    }
//    from('endorsed') {
//        // Will tell redline-rpm not to auto create directories, which
//        // is sometimes necessary to avoid rpm directory conflicts
//        addParentDirs = false
//        into '/usr/share/tomcat/endorsed'
//    }
//
//}
//
//buildRpm {
//    requires('bar', '2.2', GREATER | EQUAL)
//    requires('baz', '1.0.1', LESS)
//    link('/etc/init.d/foo’, '/opt/foo/bin/foo.init')
//}
//
//buildDeb {
//    requires('bat', '1.0.1')
//    link('/etc/init.d/foo', '/opt/foo/bin/foo.upstart')
//}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(gradleApi())
    implementation(gradleKotlinDsl())
    implementation(kotlin("reflect"))
    implementation(libs.gradle.api)
    implementation(libs.gradle)
    implementation(libs.gradle.kotlin.plugin)
    implementation(libs.gradle.detekt.plugin)
    implementation(libs.gradle.dokka.plugin)
//    implementation(libs.gradle.markdown.plugin)
    implementation(libs.gradle.kotlinter)
    implementation(libs.jsoup)
    implementation(libs.kotlin.stdlib.jdk8)
//    implementation(libs.detekt.formatting)
    implementation(libs.poet.java)
    implementation(libs.poet.kotlin)
    implementation(libs.eclipse.jgit)
    implementation(libs.okhttp3)
    implementation(libs.okhttp3.logging.interceptor)
    implementation(libs.moshi.kotlin)
    implementation(libs.kotlin.mockito)
    implementation(libs.junit)
//    implementation(libs.apk.parser)
//    implementation(libs.gradle.docker.plugin)
//    implementation(libs.korim.jvm)
//    implementation(libs.photofilter)
//    implementation(libs.dependency.analysis.gradle.plugin)
//    implementation(libs.google.api.services.androidpublisher)
//    implementation(libs.ini4j)
//    dokkaPlugin("org.jetbrains.dokka:android-documentation-plugin:1.9.20")
}

gradlePlugin {
    plugins {
        register("AppPlugin") {
            id = "AppPlugin"
            displayName = "AppPlugin"
            description = "Common application plugin to handle all stuffs needed."
            implementationClass = "org.mjdev.gradle.plugin.AppPlugin"
        }
        register("LibPlugin") {
            id = "LibPlugin"
            displayName = "LibPlugin"
            description = "Common library plugin to handle all stuffs needed."
            implementationClass = "org.mjdev.gradle.plugin.LibPlugin"
        }
    }
}