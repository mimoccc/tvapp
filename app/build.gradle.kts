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
    autoCorrectCode = false
    createDocumentation = false
    createReleaseNotes = true
    createZipRelease = false

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

    debug {
//        stringRes("app_name", "TVApp-Debug")
//        addSyncProviderAuthString("sync_auth", "sync")
    }

    release {
//        stringRes("app_name", "TVApp")
//        addSyncProviderAuthString("sync_auth", "sync")
    }

    minified {
//        stringRes("app_name", "TVApp-Minified")
//        addSyncProviderAuthString("sync_auth", "sync")
    }

}
