/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

package org.mjdev.tvapp.state

sealed class DetailsLoadingState {

    object Loading : DetailsLoadingState()

    object NotFound : DetailsLoadingState()

    class Ready(val data: Any?) : DetailsLoadingState()

    class Error(val error: Exception) : DetailsLoadingState()

}