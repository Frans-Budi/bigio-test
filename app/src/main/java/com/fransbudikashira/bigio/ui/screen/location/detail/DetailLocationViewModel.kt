package com.fransbudikashira.bigio.ui.screen.location.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fransbudikashira.bigio.ui.common.UiState
import com.fransbudikashira.core.domain.model.CompleteCharacter
import com.fransbudikashira.core.domain.model.CompleteLocation
import com.fransbudikashira.core.domain.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailLocationViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase
): ViewModel() {

    private val _location: MutableStateFlow<UiState<CompleteLocation>> =
        MutableStateFlow(UiState.Loading)
    val location: StateFlow<UiState<CompleteLocation>> get() = _location


    fun getLocationById(locationId: String) {
        viewModelScope.launch {
            characterUseCase.getLocationById(locationId)
                .catch {
                    _location.value = UiState.Error(it.message.toString())
                }
                .collect { result ->
                    _location.value = UiState.Success(result)
                }
        }
    }
}