/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.base.helpers.apps

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.mjdev.tvapp.base.interfaces.ItemWithImage
import org.mjdev.tvapp.base.interfaces.ItemWithIntent
import org.mjdev.tvapp.base.interfaces.ItemWithTitle
import java.util.Collections

@Composable
fun rememberAppsManager(): Flow<List<App>> {
    val context: Context = LocalContext.current
    return remember {
        flow {
            val packageManager = context.packageManager
            val mainIntent = Intent(Intent.ACTION_MAIN, null).apply {
                addCategory(Intent.CATEGORY_LAUNCHER)
            }
            val resolveInfo = packageManager.queryIntentActivities(mainIntent, 0)
            val apps = mutableListOf<App>()
            Collections.sort(resolveInfo, ResolveInfo.DisplayNameComparator(packageManager))
            resolveInfo.forEach { ri ->
                apps.add(App(ri, packageManager))
                emit(apps)
            }
        }.flowOn(Dispatchers.IO)
    }
}

class App(
    ri: ResolveInfo,
    packageManager: PackageManager,
) : ItemWithTitle<String>, ItemWithImage<Drawable>, ItemWithIntent {

    override var title: String? = ri.activityInfo
        .loadLabel(packageManager).toString()

    override var image: Drawable? = ri.activityInfo
        .loadBanner(packageManager) ?: ri.activityInfo
        .loadIcon(packageManager)

    override var intent: Intent? = packageManager
        .getLaunchIntentForPackage(ri.activityInfo.packageName)

}