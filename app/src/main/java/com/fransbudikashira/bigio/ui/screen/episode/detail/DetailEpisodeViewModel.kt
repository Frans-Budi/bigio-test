package com.fransbudikashira.bigio.ui.screen.episode.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fransbudikashira.bigio.ui.common.UiState
import com.fransbudikashira.core.domain.model.CompleteCharacter
import com.fransbudikashira.core.domain.model.CompleteEpisode
import com.fransbudikashira.core.domain.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailEpisodeViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase
): ViewModel() {

    private val _episode: MutableStateFlow<UiState<CompleteEpisode>> =
        MutableStateFlow(UiState.Loading)
    val episode: StateFlow<UiState<CompleteEpisode>> get() = _episode


    fun getCharacterById(episodeId: String) {
        viewModelScope.launch {
            characterUseCase.getEpisodeById(episodeId)
                .catch {
                    _episode.value = UiState.Error(it.message.toString())
                }
                .collect { result ->
                    _episode.value = UiState.Success(result)
                }
        }
    }
}