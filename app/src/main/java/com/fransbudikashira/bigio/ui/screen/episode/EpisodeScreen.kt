package com.fransbudikashira.bigio.ui.screen.episode

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.fransbudikashira.bigio.R
import com.fransbudikashira.bigio.ui.component.EpisodeItem
import com.fransbudikashira.bigio.ui.component.LoadingItem
import com.fransbudikashira.bigio.ui.component.LoadingScreen
import com.fransbudikashira.bigio.ui.component.MyTabRow
import com.fransbudikashira.bigio.ui.theme.BigioTheme
import com.fransbudikashira.core.domain.model.Episode
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EpisodeScreen(
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EpisodeViewModel = hiltViewModel(),
) {
    val listEpisodes = viewModel.getEpisodes.collectAsLazyPagingItems()
    val listFavEpisodes = listOf(
        Episode(
            id = "1",
            title = "Pilot",
            episode = "S01E01",
            airDate = "December 2, 2013",
            isFavorite = true
        ),
        Episode(
            id = "2",
            title = "Lawnmower Dog",
            episode = "S01E02",
            airDate = "December 9, 2013",
            isFavorite = true
        ),
    )

    EpisodeContent(
        listEpisode = listEpisodes,
        listFavEpisode = listFavEpisodes,
        onClick = onClick,
        onFavoriteClick = {},
        modifier = modifier
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EpisodeContent(
    pagerState: PagerState = rememberPagerState(pageCount = { 2 }),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    listEpisode: LazyPagingItems<Episode>,
    listFavEpisode: List<Episode>,
    onClick: (String) -> Unit,
    onFavoriteClick: (Boolean) -> Unit,
    modifier: Modifier
) {
    MyTabRow(
        pagerState = pagerState,
        coroutineScope = coroutineScope,
        tab1Text = stringResource(R.string.episode_list),
        tab2Text = stringResource(R.string.favorite_list),
        modifier = modifier.fillMaxSize()
    ) { page ->
        when(page) {
            0 -> {
                EpisodeList(
                    onClick = onClick,
                    onFavoriteClick = onFavoriteClick,
                    episodes = listEpisode
                )
            }
            1 -> {
                EpisodeListFavorite(
                    onClick = onClick,
                    onFavoriteClick = onFavoriteClick,
                    episodes = listFavEpisode
                )
            }
        }
    }
}

@Composable
private fun EpisodeList(
    onClick: (String) -> Unit,
    onFavoriteClick: (Boolean) -> Unit,
    episodes: LazyPagingItems<Episode>
) {
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(episodes.itemCount) { index ->
            episodes[index]?.let { item ->
                EpisodeItem(
                    title = item.title,
                    episode = item.episode,
                    airDate = item.airDate,
                    isFavorite = item.isFavorite,
                    onClick = { onClick(item.id) },
                    onFavoriteClick = onFavoriteClick,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
        }

        when(episodes.loadState.append) {
            is LoadState.Loading -> {
                item {
                    LoadingItem()
                }
            }
            is LoadState.Error -> Unit
            is LoadState.NotLoading -> Unit
        }

        when(episodes.loadState.refresh) {
            is LoadState.Loading -> {
                item {
                    LoadingScreen()
                }
            }
            is LoadState.Error -> Unit
            is LoadState.NotLoading -> Unit
        }
    }
}

@Composable
private fun EpisodeListFavorite(
    onClick: (String) -> Unit,
    onFavoriteClick: (Boolean) -> Unit,
    episodes: List<Episode>
) {
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(episodes, key = { it.id }) { item ->
            EpisodeItem(
                title = item.title,
                episode = item.episode,
                airDate = item.airDate,
                isFavorite = item.isFavorite,
                onClick = { onClick(item.id) },
                onFavoriteClick = onFavoriteClick,
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun EpisodeScreenPreview() {
    BigioTheme {}
}