package com.fransbudikashira.bigio.ui.screen.character.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.fransbudikashira.bigio.ui.component.DetailCardButton
import com.fransbudikashira.bigio.ui.component.DetailCardEpisode
import com.fransbudikashira.bigio.ui.component.ErrorScreen
import com.fransbudikashira.bigio.ui.component.LoadingScreen
import com.fransbudikashira.bigio.ui.component.NetworkImage
import com.fransbudikashira.bigio.ui.theme.BigioTheme
import com.fransbudikashira.core.domain.model.CompleteCharacter
import com.fransbudikashira.core.domain.model.DetailItemData
import com.fransbudikashira.core.domain.model.Episode
import com.fransbudikashira.core.util.DataMapper
import com.fransbudikashira.core.util.Gender
import com.fransbudikashira.core.util.StatusCharacter

@Composable
fun DetailCharacter(
    characterId: String,
    modifier: Modifier = Modifier,
    viewModel: DetailCharacterViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = characterId) {
        viewModel.getCharacterById(characterId)
    }

    val character by viewModel.character.collectAsState()

    when(character) {
        is UiState.Loading -> { LoadingScreen() }
        is UiState.Success -> {
            val data = (character as UiState.Success<CompleteCharacter>).data
            DetailCharacterContent(
                completeCharacter = data,
                modifier = modifier
            )
        }
        is UiState.Error -> {
            val errorMessage = (character as UiState.Error).errorMessage
            ErrorScreen(message = errorMessage)
        }
    }
}

@Composable
private fun DetailCharacterContent(
    completeCharacter: CompleteCharacter,
    modifier: Modifier
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        //network image
        item {
            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults
                    .cardElevation(defaultElevation = 8.dp),
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .size(160.dp)
            ) {
                NetworkImage(
                    imageUrl = completeCharacter.imageUrl,
                    contentDescription = null,
                    modifier = Modifier.weight(1f)
                )
            }
        }
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
                    text = completeCharacter.name
                ),
                DetailItemData(
                    label = stringResource(R.string.species),
                    text = completeCharacter.species
                ),
                DetailItemData(
                    label = stringResource(R.string.gender),
                    text = completeCharacter.gender.text
                ),
                DetailItemData(
                    label = "Status",
                    text = completeCharacter.statusCharacter.status
                )
            )
            DetailCard(
                listDetail = listInformation,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            )
        }
        item { Spacer(modifier = Modifier.size(12.dp)) }
        //sub title (Location)
        item {
            Text(
                text = stringResource(R.string.location),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
            )
        }
        //information location
        item {
            val listInformation = listOf(
                DetailItemData(
                    label = stringResource(R.string.origin),
                    text = completeCharacter.origin
                ),
                DetailItemData(
                    label = stringResource(R.string.location),
                    text = completeCharacter.location
                )
            )
            DetailCardButton(
                listDetail = listInformation,
                onClick = { index -> },
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            )
        }
        item { Spacer(modifier = Modifier.size(12.dp)) }
        //sub title (Episodes)
        item {
            Text(
                text = stringResource(R.string.episodes),
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
                .mapEpisodesDomainToDetailItemsData(completeCharacter.episodes)
            DetailCardEpisode(
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
private fun DetailCharacterPreview() {
    val listEpisodes = listOf(
        Episode(
            id = "1",
            title = "Pilot",
            episode = "S01E01",
            airDate = "December 2, 2013",
            isFavorite = false
        ),
        Episode(
            id = "2",
            title = "Lawnmower Dog",
            episode = "S01E02",
            airDate = "December 9, 2013",
            isFavorite = false
        ),
        Episode(
            id = "3",
            title = "Anatomy Park",
            episode = "S01E03",
            airDate = "December 16, 2013",
            isFavorite = false
        )
    )
    val character = CompleteCharacter(
        id = "1",
        imageUrl = "",
        name = "Rick Sanchez",
        species = "Human",
        statusCharacter = StatusCharacter.Alive,
        gender = Gender.Male,
        origin = "Earth (C-137)",
        location = "Citadel of Ricks",
        episodes = listEpisodes
    )

    BigioTheme {
        DetailCharacterContent(
            completeCharacter = character,
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
        )
    }
}