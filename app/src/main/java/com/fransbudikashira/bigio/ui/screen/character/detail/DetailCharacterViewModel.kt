package com.fransbudikashira.bigio.ui.screen.character.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fransbudikashira.bigio.ui.common.UiState
import com.fransbudikashira.core.data.Resource
import com.fransbudikashira.core.domain.model.CompleteCharacter
import com.fransbudikashira.core.domain.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailCharacterViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase
): ViewModel() {

    private val _character: MutableStateFlow<UiState<CompleteCharacter>> =
        MutableStateFlow(UiState.Loading)
    val character: StateFlow<UiState<CompleteCharacter>> get() = _character


    fun getCharacterById(characterId: String) {
        viewModelScope.launch {
            characterUseCase.getCharacterById(characterId)
                .catch {
                    _character.value = UiState.Error(it.message.toString())
                }
                .collect { result ->
                    _character.value = UiState.Success(result)
                }
        }
    }
}