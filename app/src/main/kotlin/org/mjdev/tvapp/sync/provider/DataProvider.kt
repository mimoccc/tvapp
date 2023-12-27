/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.sync.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.annotation.Keep
import com.squareup.moshi.Moshi
import org.mjdev.tvapp.BuildConfig
import org.mjdev.tvapp.data.local.Movie
import org.mjdev.tvapp.database.DAO
import javax.inject.Inject

@Keep
@Suppress("MemberVisibilityCanBePrivate")
class DataProvider : ContentProvider() {

    @Inject
    lateinit var dao: DAO

    @Inject
    lateinit var moshi: Moshi

    companion object {

        const val MOVIES = 0
        const val MOVIE = 1

        const val CONTENT_TYPE_MOVIES = ""
        const val CONTENT_TYPE_MOVIE = ""

        val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(BuildConfig.SYNC_AUTH, "movies", MOVIES)
            addURI(BuildConfig.SYNC_AUTH, "movie/#", MOVIE)
        }

        const val DATA = "DATA"

    }

    override fun onCreate(): Boolean = true

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? = when (uriMatcher.match(uri)) {
        else -> null
    }

    override fun getType(
        uri: Uri
    ): String? = when (uriMatcher.match(uri)) {
        MOVIES -> CONTENT_TYPE_MOVIES
        MOVIE -> CONTENT_TYPE_MOVIE
        else -> null
    }

    override fun insert(
        uri: Uri,
        values: ContentValues?
    ): Uri? = when (uriMatcher.match(uri)) {
        MOVIES ->
            throw UnsupportedOperationException("Only one movie can be inserted at one time.")

        MOVIE -> {
            if (values?.containsKey(DATA) == true) {
                moshi.adapter(Movie::class.java)
                    .fromJson(values.getAsString(DATA))
                    .also { m ->
                        if (m != null) dao.movieDao.put(m)
                    }
                uri // todo
            } else {
                throw UnsupportedOperationException("No data field with json in values.")
            }
        }

        else -> null
    }

    override fun delete(
        uri: Uri,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = when (uriMatcher.match(uri)) {
        MOVIES ->
            throw UnsupportedOperationException("Only one movie can be deleted at one time.")

        MOVIE -> {
            // todo
            -1
        }

        else -> -1
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = when (uriMatcher.match(uri)) {
        MOVIES ->
            throw UnsupportedOperationException("Only one movie can be updated at one time.")

        MOVIE -> {
            // todo
            -1
        }

        else -> -1
    }

}