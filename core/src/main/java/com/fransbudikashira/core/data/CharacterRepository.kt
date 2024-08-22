package com.fransbudikashira.core.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fransbudikashira.core.data.source.local.LocalDataSource
import com.fransbudikashira.core.data.source.remote.RemoteDataSource
import com.fransbudikashira.core.data.source.remote.network.ApiResponse
import com.fransbudikashira.core.data.source.remote.network.ApiService
import com.fransbudikashira.core.data.source.remote.paging.CharacterPagingSource
import com.fransbudikashira.core.data.source.remote.paging.EpisodePagingSource
import com.fransbudikashira.core.data.source.remote.paging.LocationPagingSource
import com.fransbudikashira.core.domain.model.Character
import com.fransbudikashira.core.domain.model.CompleteCharacter
import com.fransbudikashira.core.domain.model.CompleteEpisode
import com.fransbudikashira.core.domain.model.CompleteLocation
import com.fransbudikashira.core.domain.model.Episode
import com.fransbudikashira.core.domain.model.Location
import com.fransbudikashira.core.domain.repository.ICharacterRepository
import com.fransbudikashira.core.util.AppExecutors
import com.fransbudikashira.core.util.Constants.PAGE_SIZE
import com.fransbudikashira.core.util.DataMapper
import com.fransbudikashira.core.util.DataMapper.mapGenderToGenderSealed
import com.fransbudikashira.core.util.DataMapper.mapStatusToStatusCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepository @Inject constructor(
    private val apiService: ApiService,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): ICharacterRepository {

    override fun searchCharacter(keyword: String): Flow<List<Character>> {
        return remoteDataSource.searchCharacter(keyword).map { apiResponse ->
            when (apiResponse) {
                is ApiResponse.Success -> {
                    DataMapper.mapCharacterResultToCharacterDomain(apiResponse.data)
                }
                is ApiResponse.Error -> {
                    throw Exception(apiResponse.errorMessage)
                }
                is ApiResponse.Empty -> {
                    throw Exception("No data found")
                }
            }
        }
    }

    override fun getAllCharacter(): Flow<PagingData<Character>> {
      return Pager(
          config = PagingConfig(pageSize = PAGE_SIZE),
          pagingSourceFactory = {
              CharacterPagingSource(apiService)
          }
      ).flow
    }

    override fun getAllEpisode(): Flow<PagingData<Episode>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                EpisodePagingSource(apiService)
            }
        ).flow
    }

    override fun getAllLocation(): Flow<PagingData<Location>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                LocationPagingSource(apiService)
            }
        ).flow
    }

    override fun getCharacterById(characterId: String): Flow<CompleteCharacter> {
        return remoteDataSource.getCharacterById(characterId.toInt())
            .map { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        apiResponse.data.let {
                            CompleteCharacter(
                                id = it.id.toString(),
                                imageUrl = it.image,
                                name = it.name,
                                species = it.species,
                                statusCharacter = mapStatusToStatusCharacter(it.status),
                                gender = mapGenderToGenderSealed(it.gender),
                                origin = it.origin.name,
                                location = it.location.name,
                                episodes = remoteDataSource.getEpisodesDetails(it.episode)
                            )
                        }
                    }
                    is ApiResponse.Error -> {
                        throw Exception(apiResponse.errorMessage)
                    }
                    is ApiResponse.Empty -> {
                        throw Exception("No data found")
                    }
                }
            }
    }

    override fun getEpisodeById(episodeId: String): Flow<CompleteEpisode> {
        return remoteDataSource.getEpisodeById(episodeId.toInt())
            .map { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        apiResponse.data.let {
                            CompleteEpisode(
                                id = it.id.toString(),
                                title = it.name,
                                episode = it.episode,
                                airDate = it.airDate,
                                characters = remoteDataSource.getCharactersDetails(it.characters)
                            )
                        }
                    }
                    is ApiResponse.Error -> {
                        throw Exception(apiResponse.errorMessage)
                    }
                    is ApiResponse.Empty -> {
                        throw Exception("No data found")
                    }
                }
            }
    }

    override fun getLocationById(locationId: String): Flow<CompleteLocation> {
        return remoteDataSource.getLocationById(locationId.toInt())
            .map { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        apiResponse.data.let {
                            CompleteLocation(
                                id = it.id.toString(),
                                name = it.name,
                                type = it.type,
                                dimension = it.dimension,
                                characters = remoteDataSource.getCharactersDetails(it.residents)
                            )
                        }
                    }
                    is ApiResponse.Error -> {
                        throw Exception(apiResponse.errorMessage)
                    }
                    is ApiResponse.Empty -> {
                        throw Exception("No data found")
                    }
                }
            }
    }

    override fun getFavCharacter(): Flow<List<Character>> {
        return localDataSource.getAllCharacter().map {
            DataMapper.mapCharacterEntitiesToCharacterDomain(it)
        }
    }

    override fun addFavCharacter(character: Character) {
        val characterEntity = DataMapper.mapCharacterDomainToCharacterEntity(character)
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.insertCharacter(characterEntity)
        }
    }

    override fun deleteFavCharacter(character: Character) {
        val characterEntity = DataMapper.mapCharacterDomainToCharacterEntity(character)
        CoroutineScope(Dispatchers.IO).launch {
            localDataSource.deleteCharacter(characterEntity)
        }
    }

}