/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.tv

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import org.mjdev.tvlib.BuildConfig
import org.mjdev.tvlib.R
import org.mjdev.tvlib.extensions.ComposeExt.asException
import org.mjdev.tvlib.extensions.ComposeExt.isEditMode
import org.mjdev.tvlib.updater.AppUpdater
import org.mjdev.tvlib.updater.AppUpdater.Companion.rememberAppUpdater

@Preview
@Composable
fun AppUpdateAvailableMessage(
    title: Any? = R.string.title_app_update,
    message: Any? = R.string.msg_update_available,
    githubUser: String = "mimoccc",
    githubRepository: String = "tvapp",
    backgroundColor: Color = Color(0, 132, 0, 255),
    buttonText: Any? = R.string.bt_update,
    dismissState: MutableState<Boolean> = mutableStateOf(false),
    isDebug: Boolean = BuildConfig.DEBUG,
    appUpdater: AppUpdater = rememberAppUpdater(
        githubUser = githubUser,
        githubRepository = githubRepository,
        isDebug = isDebug
    ),
    isEdit: Boolean = isEditMode(),
    updateAvailable: State<Boolean> = appUpdater.isUpdateAvailable.collectAsState(false),
    onButtonClick: () -> Unit = { appUpdater.updateApp() },
) = ErrorMessage(
    visible = isEdit || updateAvailable.value,
    title = title,
    dismissText = buttonText,
    error = message.asException(),
    backgroundColor = backgroundColor,
    dismissible = true,
    dismissState = dismissState,
    dismissOnClick = onButtonClick
)