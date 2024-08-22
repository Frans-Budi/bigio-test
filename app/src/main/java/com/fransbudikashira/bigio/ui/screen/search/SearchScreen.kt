package com.fransbudikashira.bigio.ui.screen.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fransbudikashira.bigio.R
import com.fransbudikashira.bigio.ui.common.UiState
import com.fransbudikashira.bigio.ui.component.DetailItemCharacter
import com.fransbudikashira.bigio.ui.component.ErrorScreen
import com.fransbudikashira.core.domain.model.Character

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val query by viewModel.query.collectAsState()
    val isSearching by viewModel.isSearching.collectAsState()
    val searchResult by viewModel.searchResult.collectAsState()

    SearchContent(
        query = query,
        onQueryChange = viewModel::onQueryChange,
        onSearch = viewModel::searchCharacter,
        isSearching = isSearching,
        onClear = viewModel::clearKeyword,
        modifier = modifier
    ) {
        when (searchResult) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                val data = (searchResult as UiState.Success<List<Character>>).data
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(data, key = { it.id }) { item ->
                        Card(
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                                .clickable { onClick(item.id) }
                        ) {
                            DetailItemCharacter(
                                imageUrl = item.imageUrl,
                                name = item.name,
                                clickable = true,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                            )
                        }
                    }
                }
            }
            is UiState.Error -> {
                ErrorScreen(message = "No Character Found!")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchContent(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    isSearching: Boolean,
    onClear: () -> Unit,
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        SearchBar(
            query = query,
            onQueryChange = onQueryChange,
            onSearch = onSearch,
            shape = RoundedCornerShape(12.dp),
            shadowElevation = 2.dp,
            placeholder = {
                Text(
                    text = stringResource(R.string.search_character),
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            trailingIcon = {
                if (isSearching)
                    CircularProgressIndicator(
                        strokeWidth = 2.5.dp,
                        modifier = Modifier.size(24.dp)
                    )
                else if (query.isNotBlank())
                    IconButton(onClick = onClear) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = null,
                        )
                    }
            },
            active = false,
            onActiveChange = {},
            modifier = Modifier.height(56.dp)
        ) {}
        HorizontalDivider(modifier = Modifier
            .padding(top = 20.dp)
            .padding(vertical = 10.dp)
        )
        content()
    }
}
