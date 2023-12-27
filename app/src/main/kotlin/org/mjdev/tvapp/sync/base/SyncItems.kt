/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.sync.base

import org.mjdev.tvapp.data.local.Movie
import org.mjdev.tvlib.interfaces.ItemWithUri.Companion.hasUri

@Suppress("unused", "MemberVisibilityCanBePrivate")
class SyncItems(
    private val oldMovies: List<Movie>,
    private val newMovies: List<Movie>
) {
    val toAdd = mutableListOf<Movie>()
    val toUpdate = mutableListOf<Movie>()
    val toRemove = mutableListOf<Movie>()

    init {
        newMovies.filter { newMovie ->
            newMovie.hasUri && (oldMovies.count { oldMovie ->
                oldMovie.uri.contentEquals(newMovie.uri)
            } == 0)
        }.also { addMovies ->
            toAdd.addAll(addMovies)
        }
        newMovies.filter { newMovie ->
            newMovie.hasUri && (oldMovies.count { oldMovie ->
                oldMovie.uri.contentEquals(newMovie.uri)
            } > 0)
        }.also { updateMovies ->
            toUpdate.addAll(updateMovies)
        }
        oldMovies.filter { oldMovie ->
            (newMovies.count { newMovie ->
                newMovie.uri.contentEquals(oldMovie.uri)
            } == 0)
        }.also { addMovies ->
            toRemove.addAll(addMovies)
        }
    }
}