package com.fransbudikashira.bigio.ui.screen.location

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.fransbudikashira.bigio.R
import com.fransbudikashira.bigio.ui.component.LoadingItem
import com.fransbudikashira.bigio.ui.component.LoadingScreen
import com.fransbudikashira.bigio.ui.component.LocationItem
import com.fransbudikashira.bigio.ui.component.MyTabRow
import com.fransbudikashira.bigio.ui.theme.BigioTheme
import com.fransbudikashira.core.domain.model.Location
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LocationScreen(
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LocationViewModel = hiltViewModel(),
) {
    val listLocations = viewModel.getLocations.collectAsLazyPagingItems()
    val listFavLocations = listOf(
        Location(
            id = "1",
            name = "Earth (C-137)",
            type = "Planet",
            dimension = "Dimension C-137",
            isFavorite = true
        ),
        Location(
            id = "2",
            name = "Abadango",
            type = "Cluster",
            dimension = "unknown",
            isFavorite = true
        )
    )

    LocationContent(
        listLocation = listLocations,
        listFavLocation = listFavLocations,
        onClick = onClick,
        onFavoriteClick = {},
        modifier = modifier
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LocationContent(
    pagerState: PagerState = rememberPagerState(pageCount = { 2 }),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    listLocation: LazyPagingItems<Location>,
    listFavLocation: List<Location>,
    onClick: (String) -> Unit,
    onFavoriteClick: (Boolean) -> Unit,
    modifier: Modifier
) {
    MyTabRow(
        pagerState = pagerState,
        coroutineScope = coroutineScope,
        tab1Text = stringResource(R.string.location_list),
        tab2Text = stringResource(R.string.favorite_list),
        modifier = modifier.fillMaxSize()
    ) { page ->
        when(page) {
            0 -> {
                LocationList(
                    onClick = onClick,
                    onFavoriteClick = onFavoriteClick,
                    locations = listLocation
                )
            }
            1 -> {
                LocationListFavorite(
                    onClick = onClick,
                    onFavoriteClick = onFavoriteClick,
                    locations = listFavLocation
                )
            }
        }
    }
}

@Composable
private fun LocationList(
    onClick: (String) -> Unit,
    onFavoriteClick: (Boolean) -> Unit,
    locations: LazyPagingItems<Location>
) {
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(locations.itemCount) { index ->
            locations[index]?.let { item ->
                LocationItem(
                    name = item.name,
                    type = item.type,
                    dimension = item.dimension,
                    isFavorite = item.isFavorite,
                    onClick = { onClick(item.id) },
                    onFavoriteClick = onFavoriteClick,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
        }

        when(locations.loadState.append) {
            is LoadState.Loading -> {
                item {
                    LoadingItem()
                }
            }
            is LoadState.Error -> Unit
            is LoadState.NotLoading -> Unit
        }

        when(locations.loadState.refresh) {
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
private fun LocationListFavorite(
    onClick: (String) -> Unit,
    onFavoriteClick: (Boolean) -> Unit,
    locations: List<Location>
) {
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(locations, key = { it.id }) { item ->
            LocationItem(
                name = item.name,
                type = item.type,
                dimension = item.dimension,
                isFavorite = item.isFavorite,
                onClick = { onClick(item.id) },
                onFavoriteClick = onFavoriteClick,
                modifier = Modifier.padding(bottom = 10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LocationScreenPreview() {
    BigioTheme {}
}