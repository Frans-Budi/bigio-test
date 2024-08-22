package com.fransbudikashira.bigio.ui.screen.location

import androidx.lifecycle.ViewModel
import com.fransbudikashira.core.domain.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase
): ViewModel() {
    val getLocations = characterUseCase.getAllLocation()
}