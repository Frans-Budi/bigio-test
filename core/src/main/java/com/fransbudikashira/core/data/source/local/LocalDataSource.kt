package com.fransbudikashira.core.data.source.local

import com.fransbudikashira.core.data.source.local.entity.CharacterEntity
import com.fransbudikashira.core.data.source.local.entity.EpisodeEntity
import com.fransbudikashira.core.data.source.local.entity.LocationEntity
import com.fransbudikashira.core.data.source.local.room.CharacterDao
import com.fransbudikashira.core.data.source.local.room.EpisodeDao
import com.fransbudikashira.core.data.source.local.room.LocationDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val characterDao: CharacterDao,
    private val episodeDao: EpisodeDao,
    private val locationDao: LocationDao
) {
    //character
    fun getAllCharacter() = characterDao.getAllCharacter()
    suspend fun insertCharacter(characters: CharacterEntity) = characterDao.insertCharacter(characters)
    suspend fun deleteCharacter(character: CharacterEntity) = characterDao.deleteCharacter(character)

    //episode
    fun getAllEpisode() = episodeDao.getAllEpisode()
    suspend fun insertEpisode(episodes: EpisodeEntity) = episodeDao.insertEpisode(episodes)
    suspend fun deleteEpisode(episode: EpisodeEntity) = episodeDao.deleteEpisode(episode)

    //location
    fun getAllLocation() = locationDao.getAllLocation()
    suspend fun insertLocation(locations: LocationEntity) = locationDao.insertLocation(locations)
    suspend fun deleteLocation(location: LocationEntity) = locationDao.deleteLocation(location)

}