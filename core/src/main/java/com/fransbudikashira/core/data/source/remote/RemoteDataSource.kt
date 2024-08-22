package com.fransbudikashira.core.data.source.remote

import com.fransbudikashira.core.data.source.remote.network.ApiResponse
import com.fransbudikashira.core.data.source.remote.network.ApiService
import com.fransbudikashira.core.data.source.remote.response.CharacterResponse
import com.fransbudikashira.core.data.source.remote.response.CharacterResults
import com.fransbudikashira.core.data.source.remote.response.DetailCharacterResponse
import com.fransbudikashira.core.data.source.remote.response.DetailEpisodeResponse
import com.fransbudikashira.core.data.source.remote.response.DetailLocationResponse
import com.fransbudikashira.core.domain.model.Character
import com.fransbudikashira.core.domain.model.Episode
import com.fransbudikashira.core.util.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
){

    fun searchCharacter(keyword: String): Flow<ApiResponse<List<CharacterResults>>> {
        return flow {
            try {
                val response = apiService.searchCharacter(keyword)
                if(response.results.isNotEmpty()) {
                    val dataArray = response.results
                    emit(ApiResponse.Success(dataArray))
                } else {
                    emit(ApiResponse.Error("No Character Found!"))
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getCharacterById(characterId: Int): Flow<ApiResponse<DetailCharacterResponse>> {
        return flow {
            try {
                val response = apiService.getCharacterById(characterId)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }
    }

    fun getEpisodeById(episodeId: Int): Flow<ApiResponse<DetailEpisodeResponse>> {
        return flow {
            try {
                val response = apiService.getEpisodeById(episodeId)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }
    }

    fun getLocationById(episodeId: Int): Flow<ApiResponse<DetailLocationResponse>> {
        return flow {
            try {
                val response = apiService.getLocationById(episodeId)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }
    }

    suspend fun getEpisodesDetails(episodeUrls: List<String>): List<Episode> {
        val episodesId = episodeUrls.map { url ->
            url.substringAfterLast("/").toInt()
        }

        return episodesId.map { id ->
            DataMapper.mapDetailEpisodeResponseToEpisodeDomain(apiService.getEpisodeById(id))
        }
    }

    suspend fun getCharactersDetails(episodeCharacters: List<String>): List<Character> {
        val episodesId = episodeCharacters.map { url ->
            url.substringAfterLast("/").toInt()
        }

        return episodesId.map { id ->
            DataMapper.mapDetailCharacterResponseToCharacterDomain(apiService.getCharacterById(id))
        }
    }
}