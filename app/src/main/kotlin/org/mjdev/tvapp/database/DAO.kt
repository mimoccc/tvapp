/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.database

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.kotlin.boxFor
import org.mjdev.tvapp.BuildConfig
import org.mjdev.tvapp.data.local.Category
import org.mjdev.tvapp.data.local.Country
import org.mjdev.tvapp.data.local.Media
import org.mjdev.tvapp.data.local.Message
import org.mjdev.tvapp.data.local.MyObjectBox
import org.mjdev.tvapp.data.local.ParsedLink
import org.mjdev.tvapp.data.local.TVChannel

@Suppress("unused", "MemberVisibilityCanBePrivate")
class DAO(
    @ApplicationContext
    val context: Context,
    val isDebug: Boolean = false,
    // todo
    val autoDeleteDatabaseOnSchemaError: Boolean = true,
) {

    companion object {
        val DATABASE_NAME = BuildConfig.APPLICATION_ID.replace(".", "_")
    }

    private val store: BoxStore by lazy {
        buildStore()
    }

    val movieDao: Box<Media>
        get() = store.boxFor()

    @Suppress("unused")
    val categoryDao: Box<Category> get() = store.boxFor()

    @Suppress("unused")
    val channelsDao: Box<TVChannel>
        get() = store.boxFor()

    val messagesDao: Box<Message>
        get() = store.boxFor()

    val countriesDao: Box<Country>
        get() = store.boxFor()

    val parsedLinksDao: Box<ParsedLink>
        get() = store.boxFor()

    val allMediaItems: List<Any>
        get() = movieDao.all

    val parsedLinks: List<ParsedLink>
        get() = parsedLinksDao.all

    private fun buildStore(): BoxStore {
        return MyObjectBox.builder()
            .androidContext(context)
            .name(DATABASE_NAME)
            .apply {
//                if (isDebug) {
//                    debugFlags(1023)
//                    debugRelations()
//                }
            }
            .build()
    }

    private fun clearStore() {
        synchronized(store) {
            store.close()
            store.removeAllObjects()
//            store.deleteAllFiles()
        }
    }

}
