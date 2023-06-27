package org.mjdev.tvapp.base.page

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class PageState(
    title: Any? = null,
    icon: Any? = null
) {

    val title: MutableState<Any?> = mutableStateOf(title)
    val icon: MutableState<Any?> = mutableStateOf(icon)

}