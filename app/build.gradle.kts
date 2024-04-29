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
    autoCorrectCode = true
    createDocumentation = false
    createReleaseNotes = false
    createZipRelease = false

    createWebApp = false
    // todo
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
