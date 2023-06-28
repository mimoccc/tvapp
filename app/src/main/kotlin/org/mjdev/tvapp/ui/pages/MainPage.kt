package org.mjdev.tvapp.ui.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyRow
import androidx.tv.foundation.lazy.list.items
import androidx.tv.material3.Text
import org.mjdev.tvapp.R
import org.mjdev.tvapp.base.annotations.TvPreview
import org.mjdev.tvapp.base.extensions.ComposeExt.isEditMode
import org.mjdev.tvapp.base.extensions.HiltExt.appViewModel
import org.mjdev.tvapp.base.extensions.ModifierExt.touchable
import org.mjdev.tvapp.base.extensions.StringExt.asException
import org.mjdev.tvapp.base.navigation.Screen.Companion.open
import org.mjdev.tvapp.base.network.NetworkStatus
import org.mjdev.tvapp.base.page.Page
import org.mjdev.tvapp.base.ui.components.complex.BigCarousel
import org.mjdev.tvapp.base.ui.components.complex.ScrollableTvLazyRow
import org.mjdev.tvapp.base.ui.components.complex.Tabs
import org.mjdev.tvapp.data.Movie
import org.mjdev.tvapp.base.ui.components.card.MovieCard
import org.mjdev.tvapp.base.ui.components.complex.ErrorMessage
import org.mjdev.tvapp.base.ui.components.complex.Header
import org.mjdev.tvapp.ui.screens.DetailScreen
import org.mjdev.tvapp.ui.screens.PlayerScreen
import org.mjdev.tvapp.viewmodel.MainViewModel

@SuppressLint("ComposableNaming")
class MainPage : Page() {

    override val title: Int = R.string.title_home
    override val icon: ImageVector = Icons.Default.Home

    @TvPreview
    @Composable
    override fun Content() {

        val isEdit = isEditMode()

        val viewModel: MainViewModel = appViewModel { context ->
            MainViewModel.mockMainViewModel(context)
        }

        val categoryList = remember { viewModel.categoryList }.collectAsState()
        val featuredMovieList = remember { viewModel.featuredMovieList }.collectAsState()
        val messages = remember { viewModel.messages }.collectAsState()
        val networkState = remember { viewModel.networkInfo.networkStatus }.collectAsState(null)

        val onItemClick: (movie: Movie?) -> Unit = { movie ->
            if (movie == null) {
                viewModel.postError("No media found.".asException())
            } else if (movie.hasVideoUri) {
                navController?.open<PlayerScreen>(movie.id)
            } else {
                navController?.open<DetailScreen>(movie.id)
            }
        }

        viewModel.handleError { error ->
            screenState?.error(error)
        }

        ScrollableTvLazyRow(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {

            item {

                Header(
                    title = screenState?.titleState?.value,
                    messagesCount = messages.value.size
                )

            }

            if (isEdit || (networkState.value !is NetworkStatus.Connected)) item {
                ErrorMessage(
                    error = stringResource(R.string.error_no_network).asException(),
                    backgroundColor = Color.Black,
                    dismissible = false
                )
            }


            if (isEdit || categoryList.value.isNotEmpty()) item {
                Tabs(
                    items = categoryList.value.map { it.name }
                )
            }

            if (isEdit || featuredMovieList.value.isNotEmpty()) item {
                BigCarousel(
                    modifier = Modifier.touchable(),
                    items = featuredMovieList.value,
                    onItemClicked = onItemClick
                )
            }

            items(categoryList.value) { category ->

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 16.dp),
                    text = category.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                TvLazyRow(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.height(200.dp),
                    contentPadding = PaddingValues(
                        start = 16.dp,
                        top = 16.dp,
                        bottom = 16.dp,
                        end = 16.dp
                    )
                ) {

                    items(category.movieList) { item ->

                        MovieCard(
                            movie = item,
                            onClick = onItemClick
                        )

                    }

                }

            }

        }

    }

}