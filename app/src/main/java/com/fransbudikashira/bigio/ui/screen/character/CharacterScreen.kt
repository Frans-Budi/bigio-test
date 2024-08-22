package com.fransbudikashira.bigio.ui.screen.character

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import com.fransbudikashira.core.util.StatusCharacter
import com.fransbudikashira.bigio.ui.component.CharacterItem
import com.fransbudikashira.bigio.ui.component.LoadingItem
import com.fransbudikashira.bigio.ui.component.LoadingScreen
import com.fransbudikashira.bigio.ui.component.MyTabRow
import com.fransbudikashira.bigio.ui.theme.BigioTheme
import com.fransbudikashira.core.domain.model.Character
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterScreen(
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CharacterViewModel = hiltViewModel(),
) {
    val listCharacters = viewModel.getCharacters.collectAsLazyPagingItems()
    val listFavCharacters = listOf(
        Character(
            id = "1",
            name = "Rick Sanchez",
            status = StatusCharacter.Alive,
            species = "Human",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            isFavorite = true
        ),
        Character(
            id = "3",
            name = "Summer Smith",
            status = StatusCharacter.Dead,
            species = "Humanoid",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
            isFavorite = true
        )
    )

    CharacterContent(
        listCharacter = listCharacters,
        listFavCharacter = listFavCharacters,
        onClick = onClick,
        onFavoriteClick = {},
        modifier = modifier
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterContent(
    pagerState: PagerState = rememberPagerState(pageCount = { 2 }),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    listCharacter: LazyPagingItems<Character>,
    listFavCharacter: List<Character>,
    onClick: (String) -> Unit,
    onFavoriteClick: (Boolean) -> Unit,
    modifier: Modifier
) {
    MyTabRow(
        pagerState = pagerState,
        coroutineScope = coroutineScope,
        tab1Text = stringResource(R.string.character_list),
        tab2Text = stringResource(R.string.favorite_list),
        modifier = modifier.wrapContentSize()
    ) { page ->
        when(page) {
            0 -> {
                CharacterList(
                    onClick = onClick,
                    onFavoriteClick = onFavoriteClick,
                    characters = listCharacter
                )
            }
            1 -> {
                CharacterListFavorite(
                    onClick = onClick,
                    onFavoriteClick = onFavoriteClick,
                    characters = listFavCharacter
                )
            }
        }
    }
}

@Composable
private fun CharacterList(
    onClick: (String) -> Unit,
    onFavoriteClick: (Boolean) -> Unit,
    characters: LazyPagingItems<Character>
) {
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(characters.itemCount) { index ->
            characters[index]?.let { item ->
                CharacterItem(
                    imageUrl = item.imageUrl,
                    name = item.name,
                    species = item.species,
                    status = item.status,
                    isFavorite = item.isFavorite,
                    onClick = { onClick(item.id) },
                    onFavoriteClick = onFavoriteClick,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
        }

        when(characters.loadState.append) {
            is LoadState.Loading -> {
                item {
                    LoadingItem()
                }
            }
            is LoadState.Error -> Unit
            is LoadState.NotLoading -> Unit
        }

        when(characters.loadState.refresh) {
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
private fun CharacterListFavorite(
    onClick: (String) -> Unit,
    onFavoriteClick: (Boolean) -> Unit,
    characters: List<Character>
) {
    LazyColumn(
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(16.dp)
    ) {
        items(characters, key = { it.id }) { item ->
            CharacterItem(
                imageUrl = item.imageUrl,
                name = item.name,
                species = item.species,
                status = item.status,
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
private fun CharacterScreenPreview() {
    BigioTheme {}
}