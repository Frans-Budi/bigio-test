package com.fransbudikashira.bigio.ui.screen.location.detail

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
import com.fransbudikashira.core.domain.model.CompleteLocation
import com.fransbudikashira.core.domain.model.DetailItemData
import com.fransbudikashira.core.util.DataMapper
import com.fransbudikashira.core.util.StatusCharacter

@Composable
fun DetailLocation(
    locationId: String,
    modifier: Modifier = Modifier,
    viewModel: DetailLocationViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = locationId) {
        viewModel.getLocationById(locationId)
    }

    val episode by viewModel.location.collectAsState()

    when(episode) {
        is UiState.Loading -> { LoadingScreen() }
        is UiState.Success -> {
            val data = (episode as UiState.Success<CompleteLocation>).data
            DetailLocationContent(
                completeLocation = data,
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
private fun DetailLocationContent(
    completeLocation: CompleteLocation,
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
                    text = completeLocation.name
                ),
                DetailItemData(
                    label = stringResource(R.string.dimension),
                    text = completeLocation.dimension
                ),
                DetailItemData(
                    label = stringResource(R.string.type),
                    text = completeLocation.type
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
                .mapCharacterDomainToDetailCharacterItemData(completeLocation.characters)
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
private fun DetailLocationPreview() {
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
    val location = CompleteLocation(
        id = "1",
        name = "Earth (C-137)",
        type = "Planet",
        dimension = "Dimension C-137",
        characters = listCharacters
    )

    BigioTheme {
        DetailLocationContent(
            completeLocation = location,
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
        )
    }
}