package com.fransbudikashira.bigio.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fransbudikashira.bigio.ui.common.UiState
import com.fransbudikashira.core.domain.model.Character
import com.fransbudikashira.core.domain.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase
): ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> get() = _query

    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> get() = _isSearching

    private val _searchResult: MutableStateFlow<UiState<List<Character>>> =
        MutableStateFlow(UiState.Success(emptyList()))
    val searchResult: StateFlow<UiState<List<Character>>> get() = _searchResult

    fun onQueryChange(query: String) {
        _query.value = query
    }

    fun clearKeyword() {
        _query.value = ""
    }

    fun searchCharacter(keyword: String) {
        viewModelScope.launch {
            characterUseCase.searchCharacter(keyword)
                .onStart {
                    _searchResult.value = UiState.Loading
                    _isSearching.value = true
                }
                .catch {
                    _searchResult.value = UiState.Error(it.message.toString())
                    _isSearching.value = false
                }
                .collect { result ->
                    _searchResult.value = UiState.Success(result)
                    _isSearching.value = false
                }
        }
    }
}