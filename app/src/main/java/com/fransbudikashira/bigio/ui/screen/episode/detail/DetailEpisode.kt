package com.fransbudikashira.bigio.ui.screen.episode.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fransbudikashira.bigio.R
import com.fransbudikashira.bigio.ui.common.UiState
import com.fransbudikashira.bigio.ui.component.DetailCard
import com.fransbudikashira.bigio.ui.component.DetailCardCharacter
import com.fransbudikashira.bigio.ui.component.ErrorScreen
import com.fransbudikashira.bigio.ui.component.LoadingScreen
import com.fransbudikashira.bigio.ui.theme.BigioTheme
import com.fransbudikashira.core.domain.model.Character
import com.fransbudikashira.core.domain.model.CompleteEpisode
import com.fransbudikashira.core.domain.model.DetailItemData
import com.fransbudikashira.core.util.DataMapper
import com.fransbudikashira.core.util.StatusCharacter

@Composable
fun DetailEpisode(
    episodeId: String,
    modifier: Modifier = Modifier,
    viewModel: DetailEpisodeViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = episodeId) {
        viewModel.getCharacterById(episodeId)
    }

    val episode by viewModel.episode.collectAsState()

    when(episode) {
        is UiState.Loading -> { LoadingScreen() }
        is UiState.Success -> {
            val data = (episode as UiState.Success<CompleteEpisode>).data
            DetailEpisodeContent(
                completeEpisode = data,
                modifier = modifier
            )
        }
        is UiState.Error -> {
            val errorMessage = (episode as UiState.Error).errorMessage
            ErrorScreen(message = errorMessage)
        }
    }
}

@Composable
private fun DetailEpisodeContent(
    completeEpisode: CompleteEpisode,
    modifier: Modifier
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        //sub title (Information)
        item {
            Text(
                text = stringResource(R.string.information),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
            )
        }
        //information content
        item {
            val listInformation = listOf(
                DetailItemData(
                    label = stringResource(R.string.name),
                    text = completeEpisode.title
                ),
                DetailItemData(
                    label = stringResource(R.string.episodes),
                    text = completeEpisode.episode
                ),
                DetailItemData(
                    label = stringResource(R.string.air_date),
                    text = completeEpisode.airDate
                )
            )
            DetailCard(
                listDetail = listInformation,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            )
        }
        item { Spacer(modifier = Modifier.size(12.dp)) }
        //sub title (Characters)
        item {
            Text(
                text = stringResource(R.string.characters),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
            )
        }
        //information episodes
        item {
            val listEpisodes = DataMapper
                .mapCharacterDomainToDetailCharacterItemData(completeEpisode.characters)
            DetailCardCharacter(
                listDetail = listEpisodes,
                onClick = { index ->},
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun DetailEpisodePreview() {
    val listCharacters = listOf(
        Character(
            id = "1",
            name = "Rick Sanchez",
            status = StatusCharacter.Alive,
            species = "Human",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            isFavorite = false
        ),
        Character(
            id = "2",
            name = "Morty Smith",
            status = StatusCharacter.Unknown,
            species = "Alien",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
            isFavorite = false
        ),
        Character(
            id = "3",
            name = "Summer Smith",
            status = StatusCharacter.Dead,
            species = "Humanoid",
            imageUrl = "https://rickandmortyapi.com/api/character/avatar/3.jpeg",
            isFavorite = false
        )
    )
    val episode = CompleteEpisode(
        id = "1",
        title = "Pilot",
        episode = "S01E01",
        airDate = "December 2, 2013",
        characters = listCharacters
    )

    BigioTheme {
        DetailEpisodeContent(
            completeEpisode = episode,
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
        )
    }
}