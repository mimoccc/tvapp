/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

@file:Suppress("MemberVisibilityCanBePrivate")

package org.mjdev.tvapp.base.permission

import android.annotation.SuppressLint
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import org.mjdev.tvapp.BuildConfig

@Composable
fun manifestPermissions(): List<String> {
    val context = LocalContext.current
    return rememberSaveable {
        val packageManager = context.packageManager
        arrayListOf<String>().apply {
            val pkgInfo: PackageInfo = packageManager.getPackageInfo(
                BuildConfig.APPLICATION_ID,
                PackageManager.GET_PERMISSIONS
            )
            pkgInfo.requestedPermissions.forEach { p -> add(p) }
        }
    }
}

@Suppress("LocalVariableName")
@SuppressLint("ComposableNaming")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun rememberPermissionManager(
    permissions: List<String> = manifestPermissions(),
    onNeedToGrant: MultiplePermissionsState.(permissions: List<String>) -> Unit = {}
) {
    var _ps: MultiplePermissionsState? = null
    val lifecycleOwner = LocalLifecycleOwner.current
    val onPermissionsResult: (Map<String, Boolean>) -> Unit = { map ->
        map.filter { p ->
            !p.value
        }.filter { p ->
            permissions.contains(p.key)
        }.also { ps ->
            _ps?.also { mps ->
                onNeedToGrant(mps, ps.map { p -> p.key })
            }
        }
    }
    val permissionsState = rememberMultiplePermissionsState(
        permissions = permissions,
        onPermissionsResult = onPermissionsResult
    )
    _ps = permissionsState
    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME) {
                    permissionsState.launchMultiplePermissionRequest()
                }
            }
            lifecycleOwner.lifecycle.addObserver(observer)
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    )
}