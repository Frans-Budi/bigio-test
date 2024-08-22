package com.fransbudikashira.bigio.ui.screen.episode

import androidx.lifecycle.ViewModel
import com.fransbudikashira.core.domain.usecase.CharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val characterUseCase: CharacterUseCase
): ViewModel() {
    val getEpisodes = characterUseCase.getAllEpisode()
}