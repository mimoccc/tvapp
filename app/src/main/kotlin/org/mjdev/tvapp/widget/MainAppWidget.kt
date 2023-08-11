/*
 *  Copyright (c) Milan Jurkulák 2023.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.glance.Button
import androidx.glance.GlanceComposable
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Column
import androidx.glance.text.Text
import org.mjdev.tvapp.activity.MainActivity

class MainAppWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            WidgetContent()
        }
    }

    @Preview
    @GlanceComposable
    @Composable
    fun WidgetContent() {
        Column {
            Text(
                "Hello World",
                modifier = GlanceModifier.clickable {
                    actionStartActivity<MainActivity>()
                }

            )
            Button(
                text = "PlayPause",
                onClick = {
                    actionRunCallback<PlayPauseAction>()
                }
            )
        }
    }
}