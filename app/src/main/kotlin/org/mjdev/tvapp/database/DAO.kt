@file:Suppress("unused")

package org.mjdev.tvapp.database

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.kotlin.awaitCallInTx
import io.objectbox.kotlin.boxFor
import org.mjdev.tvapp.BuildConfig
import org.mjdev.tvapp.data.local.Category
import org.mjdev.tvapp.data.local.Country
import org.mjdev.tvapp.data.local.Message
import org.mjdev.tvapp.data.local.Movie
import org.mjdev.tvapp.data.local.MyObjectBox
import org.mjdev.tvapp.data.local.TVChannel

class DAO(
    @ApplicationContext
    val context: Context,
) {

    companion object {

        val DATABASE_NAME = BuildConfig.APPLICATION_ID
            .replace(".", "_")

        suspend inline fun <reified V, T> Box<T>.tx(
            crossinline transaction: Box<T>.() -> V
        ) = store.awaitCallInTx {
            transaction(this)
        }

        // todo remove reflection
        inline fun <reified T> Box<T>.findById(id: Long?): T? = if (id == null) all.firstOrNull()
        else all.firstOrNull { o -> o.property("id") == id }

        // todo remove reflection
        inline fun <reified T> T.property(name: String): Any? = T::class.members.firstOrNull {
            it.name == name
        }?.call(this)

    }

    private val store: BoxStore by lazy {
        MyObjectBox.builder()
            .androidContext(context)
            .name(DATABASE_NAME)
            .build()
    }

    val movieDao: Box<Movie> by lazy {
        store.boxFor()
    }

    val categoryDao: Box<Category> by lazy {
        store.boxFor()
    }

    val channelsDao: Box<TVChannel> by lazy {
        store.boxFor()
    }

    val messagesDao: Box<Message> by lazy {
        store.boxFor()
    }

    val countriesDao: Box<Country> by lazy {
        store.boxFor()
    }

}