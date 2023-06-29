/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.mock

import android.net.Uri
import org.mjdev.tvapp.data.Movie

@Suppress("SpellCheckingInspection")
internal object MovieList {

    val categories = listOf(
        "Category Zero",
        "Category One",
        "Category Two",
        "Category Three",
        "Category Four",
        "Category Five"
    )

    private val all: List<Movie> by lazy {
        setupMovies()
    }

    val featured by lazy {
        (all + all).shuffled()
    }

    private val categoryMoviesMap: Map<String, List<Movie>> by lazy {
        categories.associateWith { all.shuffled() }
    }

    fun getByCategory(category: String?): List<Movie> {
        return categoryMoviesMap[category] ?: all
    }

    fun findById(id: Long): Movie? {
        return all.find { id == it.id }
    }

    private fun setupMovies(): List<Movie> {
        var count: Long = 0

        val title = arrayOf(
            "Zeitgeist 2010_ Year in Review",
            "Google Demo Slam_ 20ft Search",
            "Introducing Gmail Blue",
            "Introducing Google Fiber to the Pole",
            "Introducing Google Nose"
        )

        val description = "Fusce id nisi turpis. Praesent viverra bibendum semper. " +
                "Donec tristique, orci sed semper lacinia, quam erat rhoncus massa, non congue tellus est " +
                "quis tellus. Sed mollis orci venenatis quam scelerisque accumsan. Curabitur a massa sit " +
                "amet mi accumsan mollis sed et magna. Vivamus sed aliquam risus. Nulla eget dolor in elit " +
                "facilisis mattis. Ut aliquet luctus lacus. Phasellus nec commodo erat. Praesent tempus id " +
                "lectus ac scelerisque. Maecenas pretium cursus lectus id volutpat."
        val studio = arrayOf(
            "Studio Zero",
            "Studio One",
            "Studio Two",
            "Studio Three",
            "Studio Four"
        )
        val videoUrl = arrayOf(
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search.mp4",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue.mp4",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole.mp4",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose.mp4"
        )
        val bgImageUrl = arrayOf(
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/bg.jpg",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search/bg.jpg",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue/bg.jpg",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole/bg.jpg",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose/bg.jpg"
        )
        val cardImageUrl = arrayOf(
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review/card.jpg",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/Demo%20Slam/Google%20Demo%20Slam_%2020ft%20Search/card.jpg",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Gmail%20Blue/card.jpg",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Fiber%20to%20the%20Pole/card.jpg",
            "https://commondatastorage.googleapis.com/android-tv/Sample%20videos/April%20Fool's%202013/Introducing%20Google%20Nose/card.jpg"
        )

        val list = title.indices.map {
            Movie(
                id = count++,
                title = title[it],
                description = description,
                studio = studio[it],
                videoUri = Uri.parse(videoUrl[it]),
                imageUrl = cardImageUrl[it],
                backgroundImageUrl = bgImageUrl[it],
            )
        }

        return list
    }

}