/*
 * Copyright (c) Milan Jurkulák 2023.
 * Contact:
 * e: mimoccc@gmail.com
 * e: mj@mjdev.org
 * w: https://mjdev.org
 */

@file:Suppress("unused")

package org.mjdev.tvapp.base.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import kotlinx.coroutines.flow.Flow
import org.mjdev.tvapp.BuildConfig

@Suppress("MemberVisibilityCanBePrivate")
object ComposeExt {

    @Composable
    fun isDebug() = BuildConfig.DEBUG

    @Composable
    fun isEditMode() = LocalInspectionMode.current

    @Composable
    fun <T> Flow<T>?.collectAsState(): State<T>? = this?.collectAsState()

    @Composable
    inline fun <reified T> textFrom(text: T?): String = when (text) {
        null -> ""
        is Unit -> ""
        is Int -> LocalContext.current.getString(text)
        is String -> text
        else -> text.toString()
    }

//    @Composable
//    fun Any?.toDrawable(): Drawable {
//        val context = LocalContext.current
//        val coroutine = rememberCoroutineScope()
//        return when (this) {
//            is Int -> ContextCompat.getDrawable(context, this) ?: ColorDrawable(0)
//            is Color -> ColorDrawable(this.toArgb())
//            is Drawable -> this
//            is Bitmap -> BitmapDrawable(context.resources, this)
//            else -> {
//                runBlocking {
//                    withContext(coroutine.coroutineContext) {
//                        ImageLoader(context).execute(
//                            ImageRequest.Builder(context)
//                                .data(this)
//                                .build()
//                        ).drawable
//                    }!!
//                }
//            }
//        }
//    }

}