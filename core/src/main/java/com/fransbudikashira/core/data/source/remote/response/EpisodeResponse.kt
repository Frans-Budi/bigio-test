package com.fransbudikashira.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class EpisodeResponse(

	@field:SerializedName("results")
	val results: List<EpisodeResultsItem>,

	@field:SerializedName("info")
	val info: InfoEpisodeData
)

data class InfoEpisodeData(

	@field:SerializedName("next")
	val next: String,

	@field:SerializedName("pages")
	val pages: Int,

	@field:SerializedName("prev")
	val prev: String,

	@field:SerializedName("count")
	val count: Int
)

data class EpisodeResultsItem(

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
