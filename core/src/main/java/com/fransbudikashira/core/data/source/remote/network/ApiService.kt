package com.fransbudikashira.core.data.source.remote.network

import com.fransbudikashira.core.data.source.remote.response.CharacterResponse
import com.fransbudikashira.core.data.source.remote.response.DetailCharacterResponse
import com.fransbudikashira.core.data.source.remote.response.DetailEpisodeResponse
import com.fransbudikashira.core.data.source.remote.response.DetailLocationResponse
import com.fransbudikashira.core.data.source.remote.response.EpisodeResponse
import com.fransbudikashira.core.data.source.remote.response.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getAllCharacter(
        @Query("page") page: Int
    ): CharacterResponse

    @GET("character/")
    suspend fun searchCharacter(
        @Query("name") name: String
    ): CharacterResponse

    @GET("character/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: Int
    ): DetailCharacterResponse

    @GET("episode")
    suspend fun getAllEpisode(
        @Query("page") page: Int
    ): EpisodeResponse

    @GET("episode/{episodeId}")
    suspend fun getEpisodeById(
        @Path("episodeId") episodeId: Int
    ): DetailEpisodeResponse

    @GET("location")
    suspend fun getAllLocation(
        @Query("page") page: Int
    ): LocationResponse

    @GET("location/{locationId}")
    suspend fun getLocationById(
        @Path("locationId") locationId: Int
    ): DetailLocationResponse
}