package com.fransbudikashira.core.util

import com.fransbudikashira.core.data.source.local.entity.CharacterEntity
import com.fransbudikashira.core.data.source.remote.response.CharacterResults
import com.fransbudikashira.core.data.source.remote.response.DetailCharacterResponse
import com.fransbudikashira.core.data.source.remote.response.DetailEpisodeResponse
import com.fransbudikashira.core.data.source.remote.response.EpisodeResultsItem
import com.fransbudikashira.core.data.source.remote.response.LocationResultsItem
import com.fransbudikashira.core.domain.model.Character
import com.fransbudikashira.core.domain.model.DetailCharacterItemData
import com.fransbudikashira.core.domain.model.DetailItemData
import com.fransbudikashira.core.domain.model.Episode
import com.fransbudikashira.core.domain.model.Location

object DataMapper {

    fun mapEpisodesDomainToDetailItemsData(input: List<Episode>): List<DetailItemData> =
        input.map {
            DetailItemData(
                label = it.title,
                text = it.episode
            )
        }

    fun mapCharacterDomainToDetailCharacterItemData(input: List<Character>) : List<DetailCharacterItemData> =
        input.map {
            DetailCharacterItemData(
                imageUrl = it.imageUrl,
                name = it.name
            )
        }

    fun mapCharacterResultToCharacterDomain(input: List<CharacterResults>): List<Character> =
        input.map {
            Character(
                id = it.id.toString(),
                imageUrl = it.image,
                name = it.name,
                species = it.species,
                status = mapStatusToStatusCharacter(it.status),
                isFavorite = false
            )
        }

    fun mapEpisodeResultToEpisodeDomain(input: List<EpisodeResultsItem>): List<Episode> =
        input.map {
            Episode(
                id = it.id.toString(),
                title = it.name,
                episode = it.episode,
                airDate = it.airDate,
                isFavorite = false
            )
        }

    fun mapDetailEpisodeResponseToEpisodeDomain(input: DetailEpisodeResponse): Episode =
        Episode(
            id = input.id.toString(),
            title = input.name,
            episode = input.episode,
            airDate = input.airDate,
            isFavorite = false
        )

    fun mapDetailCharacterResponseToCharacterDomain(input: DetailCharacterResponse): Character =
        Character(
            id = input.id.toString(),
            imageUrl = input.image,
            name = input.name,
            species = input.species,
            status = mapStatusToStatusCharacter(input.status),
            isFavorite = false
        )

    fun mapLocationResultToLocationDomain(input: List<LocationResultsItem>): List<Location> =
        input.map {
            Location(
                id = it.id.toString(),
                name = it.name,
                type = it.type,
                dimension = it.dimension,
                isFavorite = false
            )
        }

    fun mapCharacterEntitiesToCharacterDomain(input: List<CharacterEntity>): List<Character> =
        input.map {
            Character(
                id = it.id,
                imageUrl = it.imageUrl,
                name = it.name,
                species = it.species,
                status = mapStatusToStatusCharacter(it.status),
                isFavorite = true
            )
        }

    fun mapCharacterDomainToCharacterEntity(input: Character): CharacterEntity =
        CharacterEntity(
            id = input.id,
            imageUrl = input.imageUrl,
            name = input.name,
            species = input.species,
            status = input.status.status,
        )

    fun mapStatusToStatusCharacter(status: String): StatusCharacter {
        return when (status) {
            "Alive" -> StatusCharacter.Alive
            "Unknown" -> StatusCharacter.Unknown
            "Dead" -> StatusCharacter.Dead
            else -> StatusCharacter.Unknown
        }
    }

    fun mapGenderToGenderSealed(gender: String): Gender {
        return when (gender) {
            "Male" -> Gender.Male
            "Female" -> Gender.Female
            "Unknown" -> Gender.Unknown
            else -> Gender.Unknown
        }
    }
}