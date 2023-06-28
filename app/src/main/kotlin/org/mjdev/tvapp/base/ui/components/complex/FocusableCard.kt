package org.mjdev.tvapp.base.ui.components.complex

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.CardBorder
import androidx.tv.material3.CardColors
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.CardGlow
import androidx.tv.material3.CardScale
import androidx.tv.material3.CardShape
import androidx.tv.material3.CompactCard
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Glow
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.extensions.ModifierExt.touchable
import org.mjdev.tvapp.base.ui.components.image.ImageAny
import org.mjdev.tvapp.base.ui.components.text.TextAny
import org.mjdev.tvapp.data.Movie

@OptIn(ExperimentalTvMaterial3Api::class)
@SuppressLint("ModifierParameter")
@Preview
@Composable
fun FocusableCard(
    movie: Movie? = null,
    modifier: Modifier = Modifier,
    focused: Boolean = false,
    contentScale: ContentScale = ContentScale.Crop,
    placeHolder: Int = R.drawable.placeholder,
    scale: CardScale = CardDefaults.scale(),
    shape: CardShape = CardDefaults.shape(),
    colors: CardColors = CardDefaults.colors(),
    border: CardBorder = CardDefaults.border(),
    glow: CardGlow = CardDefaults.glow(),
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onFocus: (movie: Movie?) -> Unit = {},
    onClick: (movie: Movie?) -> Unit = {},
) {
    val focusState = remember { mutableStateOf<FocusState?>(null) }
    val isFocused: () -> Boolean = {
        ((focusState.value?.isFocused == true) || (focusState.value?.hasFocus == true))
    }

    CompactCard(
        interactionSource = interactionSource,
        modifier = modifier
            .onFocusChanged { state ->
                focusState.value = state
            }
            .touchable {
                if (isFocused())
                    onClick(movie)
                else
                    onFocus(movie)
            },
        image = {
            ImageAny(
                modifier = modifier,
                src = movie?.cardImageUrl,
                contentDescription = movie?.description,
                contentScale = contentScale,
                placeholder = placeHolder
            )
        },
        title = {
            TextAny(
                modifier = Modifier.padding(4.dp),
                text = movie?.title
            )
        },
        subtitle = {
            TextAny(
                modifier = Modifier.padding(4.dp),
                text = movie?.studio
            )
        },
        description = {
        },
        onClick = {
            onClick(movie)
        },
        glow = CardDefaults.glow(
            glow = Glow.None,
            focusedGlow = Glow(
                elevationColor = Color.Green,
                elevation = 10.dp
            ),
            pressedGlow = Glow.None
        ),
        scale = scale,
    )

}