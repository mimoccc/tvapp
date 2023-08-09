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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import org.mjdev.tvlib.interfaces.ItemWithImage
import org.mjdev.tvlib.interfaces.ItemWithIntent
import org.mjdev.tvlib.interfaces.ItemWithTitle
import java.util.Collections

@Composable
fun rememberAppsManager(
    vararg excluded: ComponentName
): Flow<List<App>> {
    val context: Context = LocalContext.current
    return remember {
        appsManager(context, *excluded)
    }
}

fun appsManager(
    context: Context,
    vararg excluded: ComponentName
) = channelFlow {
    val packageManager = context.packageManager
    val mainIntent = Intent(Intent.ACTION_MAIN, null).apply {
        addCategory(Intent.CATEGORY_LAUNCHER)
    }
    val isNotExcluded: (ri: ResolveInfo) -> Boolean = { ri ->
        !excluded.any { cn ->
            cn.packageName == ri.activityInfo.packageName &&
                    cn.shortClassName == ri.activityInfo.name
        }
    }
    val title: (ri: ResolveInfo) -> String = { ri ->
        ri.activityInfo.loadLabel(packageManager).toString()
    }
    val image: (ri: ResolveInfo) -> Drawable = { ri ->
        ri.activityInfo
            .loadBanner(packageManager) ?: ri.activityInfo
            .loadIcon(packageManager)
    }
    val intent: (ri: ResolveInfo) -> Intent = { ri ->
        Intent().apply {
            setClassName(
                ri.activityInfo.packageName,
                ri.activityInfo.name
            )
        }
    }
    packageManager.queryIntentActivities(
        mainIntent,
        0
    ).apply {
        Collections.sort(
            this,
            ResolveInfo.DisplayNameComparator(packageManager)
        )
    }.mapNotNull { ri ->
        if (isNotExcluded(ri)) {
            App(title(ri), image(ri), intent(ri))
        } else {
            null
        }
    }.also { list ->
        send(list)
    }
}

data class App(
    override var title: String?,
    override var image: Drawable?,
    override var intent: Intent?,
) : ItemWithTitle<String>, ItemWithImage<Drawable>, ItemWithIntent