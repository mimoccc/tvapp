/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.mjdev.tvapp.api.MovieAPI
import org.mjdev.tvapp.base.helpers.Result.Companion.unwrapOr
import org.mjdev.tvapp.data.Category
import org.mjdev.tvapp.data.Movie
import java.lang.Integer.max
import java.lang.Integer.min
import javax.inject.Inject

@Suppress("unused")
class MovieRepository @Inject constructor(
    private val dataSource: MovieAPI
) : IRepository {

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    private val categoryMovieListMap: MutableMap<Category, List<Movie>> = mutableMapOf()
    private val idMovieMap: MutableMap<Long, Movie?> = mutableMapOf()

    private var categoryList: List<Category> = listOf()

    private val categoryMovieListMapMutex = Mutex()
    private val idMovieMapMutex = Mutex()
    private val categoryListMutex = Mutex()

    override suspend fun getFeaturedMovieList(): List<Movie> {
        val movieList = dataSource.loadFeaturedMovieList().unwrapOr(listOf())
        updateCache(movieList)
        return movieList
    }

    override suspend fun getCategoryList(): List<Category> {
        if (categoryList.isEmpty()) {
            categoryListMutex.withLock {
                categoryList = dataSource.loadCategoryList().unwrapOr(categoryList)
            }
        }
        prefetchMovieListByCategory(category = categoryList[0])
        return categoryListMutex.withLock { categoryList }
    }

    override suspend fun findMovieById(id: Long?): Movie? {
        idMovieMapMutex.withLock {
            if (id == null) return null
            if (!idMovieMap.contains(id)) {
                idMovieMap[id] = dataSource.findMovieById(id).unwrapOr(null)
            }
        }
        return idMovieMapMutex.withLock { idMovieMap[id] }
    }

    suspend fun getMovieListByCategory(category: Category): List<Movie> {
        loadMovieListByCategory(category)
        prefetchNeighborCategories(category = category)
        return categoryMovieListMapMutex.withLock {
            categoryMovieListMap[category] ?: emptyList()
        }
    }

    private suspend fun loadMovieListByCategory(category: Category, force: Boolean = false) {
        if (force || !categoryMovieListMap.contains(category)) {
            updateCache(
                category = category,
                movieList = dataSource.getMovieListByCategory(category.name).unwrapOr(listOf())
            )
        }
    }

    private suspend fun updateCache(category: Category, movieList: List<Movie>) {
        categoryMovieListMapMutex.withLock {
            categoryMovieListMap[category] = movieList
        }
        updateCache(movieList)
    }

    private suspend fun updateCache(movieList: List<Movie>) {
        idMovieMapMutex.withLock {
            movieList.forEach {
                idMovieMap[it.id] = it
            }
        }
    }

    private fun prefetchMovieListByCategory(category: Category) {
        CoroutineScope(dispatcher).launch {
            loadMovieListByCategory(category)
        }
    }

    private fun prefetchNeighborCategories(
        category: Category,
        neighborCount: Int = 1,
    ) {
        val indexForCategory = max(categoryList.indexOf(category), 0)
        val neighbors =
            (indexForCategory..min(
                indexForCategory + neighborCount,
                categoryList.size - 1
            )).map { categoryList[it] }
        CoroutineScope(dispatcher).launch {
            neighbors.forEach { loadMovieListByCategory(it) }
        }
    }

}