/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

plugins {
    id("AppPlugin")
}

appConfig {
    namespace = "org.mjdev.tvapp"
    description = "Smart TV android app for any android device"
    createWebSiteFromGit = false
    // todo
    default {
//        buildConfigString(
//            "IPTV_API_URL" to "https://iptv-org.github.io/api/",
//            "GITHUB_USER" to "mimoccc",
//            "GITHUB_REPOSITORY" to "tvapp"
//        )
//        manifestPlaceholders(
//            "auth0Domain" to "@string/com_auth0_domain",
//            "auth0Scheme" to "demo"
//        )
    }

    // todo
    debug {
//        println("creating test bt config field")
//        buildConfigField("String" , "TEST", "\"TEST\"")
//        stringRes("app_name", "TVApp-Debug")
//        addSyncProviderAuthString("sync_auth", "sync")
    }

    // todo
    release {
//        stringRes("app_name", "TVApp")
//        addSyncProviderAuthString("sync_auth", "sync")
    }

    // todo
    minified {
//        stringRes("app_name", "TVApp-Minified")
//        addSyncProviderAuthString("sync_auth", "sync")
    }

}

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
