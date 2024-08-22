package com.fransbudikashira.bigio.ui.screen.character

import androidx.lifecycle.ViewModel
import com.fransbudikashira.core.domain.model.Character
import com.fransbudikashira.core.domain.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase
): ViewModel() {
    val getCharacters = characterUseCase.getAllCharacter()

    fun addFavCharacter(character: Character) = characterUseCase.addFavCharacter(character)
}