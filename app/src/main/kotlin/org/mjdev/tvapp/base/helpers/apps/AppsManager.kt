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
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import org.mjdev.tvapp.base.interfaces.ItemWithImage
import org.mjdev.tvapp.base.interfaces.ItemWithIntent
import org.mjdev.tvapp.base.interfaces.ItemWithTitle
import java.util.Collections

// todo saveable, parcelable is too big
@Composable
fun rememberAppsManager(): List<App> {
    val context: Context = LocalContext.current
    return remember {
        val packageManager = context.packageManager
        val mainIntent = Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        val resolveInfo = packageManager.queryIntentActivities(mainIntent, 0)
        Collections.sort(resolveInfo, ResolveInfo.DisplayNameComparator(packageManager))
        mutableListOf<App>().apply {
            resolveInfo.forEach { ri ->
                val title = ri.activityInfo.loadLabel(packageManager).toString()
                val image = ri.activityInfo
                    .loadBanner(packageManager) ?: ri.activityInfo
                    .loadIcon(packageManager)
                val intent: Intent? =
                    packageManager.getLaunchIntentForPackage(ri.activityInfo.packageName)
                add(App(title, image, intent))
            }
        }
    }
}

data class App(
    override var title: String?,
    override var image: Drawable?,
    override var intent: Intent?,
) : ItemWithTitle<String>, ItemWithImage<Drawable>, ItemWithIntent