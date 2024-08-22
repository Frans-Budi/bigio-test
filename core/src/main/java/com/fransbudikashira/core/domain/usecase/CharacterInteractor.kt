package com.fransbudikashira.core.domain.usecase

import com.fransbudikashira.core.domain.model.Character
import com.fransbudikashira.core.domain.repository.ICharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterInteractor @Inject constructor(
    private val characterRepository: ICharacterRepository
): CharacterUseCase  {

    override fun searchCharacter(keyword: String) = characterRepository.searchCharacter(keyword)

    override fun getAllCharacter() = characterRepository.getAllCharacter()

    override fun getAllEpisode() = characterRepository.getAllEpisode()

    override fun getAllLocation() = characterRepository.getAllLocation()

    override fun getCharacterById(characterId: String) = characterRepository.getCharacterById(characterId)

    override fun getEpisodeById(episodeId: String) = characterRepository.getEpisodeById(episodeId)

    override fun getLocationById(locationId: String) = characterRepository.getLocationById(locationId)

    override fun getFavCharacter() = characterRepository.getFavCharacter()
    override fun addFavCharacter(character: Character) = characterRepository.addFavCharacter(character)
    override fun deleteFavCharacter(character: Character) = characterRepository.deleteFavCharacter(character)
}