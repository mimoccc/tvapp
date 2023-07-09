@file:Suppress("unused")

package org.mjdev.tvapp.database

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.kotlin.boxFor
import org.mjdev.tvapp.BuildConfig
import org.mjdev.tvapp.data.Message
import org.mjdev.tvapp.data.Movie
import org.mjdev.tvapp.data.MyObjectBox

class DAO(
    @ApplicationContext
    val context: Context,
) {

    companion object {

        val DATABASE_NAME = BuildConfig.APPLICATION_ID
            .replace(".", "_")

    }

    private val store: BoxStore by lazy {
        MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .name(DATABASE_NAME)
            .build()
    }

    val movieDao: Box<Movie> by lazy {
        store.boxFor()
    }

    val messagesDao: Box<Message> by lazy {
        store.boxFor()
    }

}