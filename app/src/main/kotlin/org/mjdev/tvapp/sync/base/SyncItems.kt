/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.sync.base

import org.mjdev.tvapp.data.local.Media
import org.mjdev.tvlib.interfaces.ItemWithUri.Companion.hasUri

@Suppress("unused", "MemberVisibilityCanBePrivate")
class SyncItems(
    private val oldMovies: List<Media>,
    private val newMovies: List<Media>
) {

    val toAdd = mutableListOf<Media>()
    val toUpdate = mutableListOf<Media>()
    val toRemove = mutableListOf<Media>()

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
