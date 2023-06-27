package org.mjdev.tvapp.base.page

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableIntStateOf

@Suppress("MemberVisibilityCanBePrivate", "unused")
@SuppressLint("AutoboxingStateValueProperty")
class PagerState(
    val startIndex: Int = 0
) {

    val history = mutableListOf<Int>()
    val index = mutableIntStateOf(startIndex)
    val currentPage get() = if (history.size > 0) history[history.size - 1] else startIndex


    fun goToPage(pageIndex: Int) {
        history.add(currentPage)
        index.value = pageIndex
    }

    fun goBack() {
        history.removeAt(history.size-1)
        index.value = currentPage
    }

}