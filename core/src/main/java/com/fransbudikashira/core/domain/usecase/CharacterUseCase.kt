package com.fransbudikashira.core.domain.usecase

import androidx.paging.PagingData
import com.fransbudikashira.core.domain.model.Character
import com.fransbudikashira.core.domain.model.CompleteCharacter
import com.fransbudikashira.core.domain.model.CompleteEpisode
import com.fransbudikashira.core.domain.model.CompleteLocation
import com.fransbudikashira.core.domain.model.Episode
import com.fransbudikashira.core.domain.model.Location
import kotlinx.coroutines.flow.Flow

interface CharacterUseCase {

    fun searchCharacter(keyword: String): Flow<List<Character>>

    fun getAllCharacter(): Flow<PagingData<Character>>

    fun getAllEpisode(): Flow<PagingData<Episode>>

    fun getAllLocation(): Flow<PagingData<Location>>

    fun getCharacterById(characterId: String): Flow<CompleteCharacter>

    fun getEpisodeById(episodeId: String): Flow<CompleteEpisode>

    fun getLocationById(locationId: String): Flow<CompleteLocation>

    fun getFavCharacter(): Flow<List<Character>>
    fun addFavCharacter(character: Character)
    fun deleteFavCharacter(character: Character)
}