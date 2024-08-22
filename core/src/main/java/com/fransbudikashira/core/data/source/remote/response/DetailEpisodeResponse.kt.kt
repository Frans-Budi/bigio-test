package com.fransbudikashira.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailEpisodeResponse(

	@field:SerializedName("air_date")
	val airDate: String,

	@field:SerializedName("characters")
	val characters: List<String>,

	@field:SerializedName("created")
	val created: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("episode")
	val episode: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("url")
	val url: String
)
