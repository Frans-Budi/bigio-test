package com.fransbudikashira.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CharacterResponse(

    @field:SerializedName("info")
    val info: InfoCharacterData,

    @field:SerializedName("results")
    val results: List<CharacterResults>,
)

data class CharacterResults(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("species")
    val species: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("gender")
    val gender: String,

    @field:SerializedName("origin")
    val origin: OriginData,

    @field:SerializedName("location")
    val location: LocationData,

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("episode")
    val episode: List<String>,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("created")
    val created: String,
)

data class LocationData(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("url")
    val url: String,
)

data class OriginData(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("url")
    val url: String,
)

data class InfoCharacterData(
    @field:SerializedName("count")
    val count: Int,

    @field:SerializedName("pages")
    val pages: Int,

    @field:SerializedName("next")
    val next: String,

    @field:SerializedName("prev")
    val prev: String,
)
