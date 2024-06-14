/*
 *  Copyright (c) Milan Jurkulák 2024.
 *  Contact:
 *  e: mimoccc@gmail.com
 *  e: mj@mjdev.org
 *  w: https://mjdev.org
 */

package org.mjdev.tvlib.ui.components.button

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Icon
import androidx.tv.material3.Text
import org.mjdev.tvlib.annotations.Previews

@Previews
@Composable
fun ShareButton(
    modifier: Modifier = Modifier,
    data: String = "Share",
    type: MimeType = MimeType.PlainText
) {
    val context = LocalContext.current
    val shareIntent = remember(type, data) {
        Intent(Intent.ACTION_SEND).let { intent ->
            intent.putExtra(Intent.EXTRA_TEXT, data)
            intent.type = type.mime
            Intent.createChooser(intent, null)
        }
//        Intent(
//            Intent.ACTION_VIEW,
//            Uri.parse("https://www.")
//        )
    }
    Row(
        modifier = modifier.clickable {
            context.startActivity(shareIntent, null)
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = null
        )
        Text("Share", modifier = Modifier.padding(start = 8.dp))
    }
}

enum class MimeType(val mime: String) {
    PlainText("text/plain")
}