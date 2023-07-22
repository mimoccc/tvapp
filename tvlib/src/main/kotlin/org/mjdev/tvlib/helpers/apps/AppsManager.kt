/*
 * Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.helpers.apps

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.interfaces.ItemWithIntent
import org.mjdev.tvlib.interfaces.ItemWithTitle
import java.util.Collections

@Composable
fun rememberAppsManager(
    vararg excluded: ComponentName
): List<App> {
    val context: Context = LocalContext.current
    return remember {
        AppsManager(context, *excluded)
    }
}

class AppsManager(
    context: Context,
    vararg excluded: ComponentName
) : ArrayList<App>() {
    init {
        val packageManager = context.packageManager
        val mainIntent = Intent(Intent.ACTION_MAIN, null).apply {
            addCategory(Intent.CATEGORY_LAUNCHER)
        }
        val resolveInfo = packageManager.queryIntentActivities(mainIntent, 0)
        Collections.sort(resolveInfo, ResolveInfo.DisplayNameComparator(packageManager))
        resolveInfo.forEach { ri ->
            val isExcluded = excluded.any { cn ->
                cn.packageName == ri.activityInfo.packageName &&
                        cn.shortClassName == ri.activityInfo.name
            }
            if (!isExcluded) {
                val title = ri.activityInfo.loadLabel(packageManager).toString()
                val image = ri.activityInfo
                    .loadBanner(packageManager) ?: ri.activityInfo
                    .loadIcon(packageManager)
                val intent = Intent().apply {
                    setClassName(
                        ri.activityInfo.packageName,
                        ri.activityInfo.name
                    )
                }
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