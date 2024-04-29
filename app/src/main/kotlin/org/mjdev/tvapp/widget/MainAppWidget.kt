/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvapp.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.glance.Button
import androidx.glance.GlanceComposable
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.text.Text
import org.mjdev.tvapp.R
import org.mjdev.tvapp.activity.MainActivity

class MainAppWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            WidgetContent()
        }
    }

    @GlanceComposable
    @Composable
    fun WidgetContent() {
        Box(
            modifier = GlanceModifier.fillMaxSize()
        ) {
            Image(
                modifier = GlanceModifier.fillMaxWidth(),
                provider = ImageProvider(R.drawable.audio_player_default_show_bg) ,
                contentDescription = ""
            )
            Image(
                modifier = GlanceModifier.fillMaxSize(),
                provider = ImageProvider(R.drawable.bgcase) ,
                contentDescription = ""
            )
            Column(
                modifier = GlanceModifier.fillMaxSize()
            ) {
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

}