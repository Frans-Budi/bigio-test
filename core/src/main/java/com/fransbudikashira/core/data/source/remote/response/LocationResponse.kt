package com.fransbudikashira.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LocationResponse(

	@field:SerializedName("results")
	val results: List<LocationResultsItem>,

	@field:SerializedName("info")
	val info: InfoLocationData
)

data class InfoLocationData(

	@field:SerializedName("next")
	val next: String,

	@field:SerializedName("pages")
	val pages: Int,

	@field:SerializedName("prev")
	val prev: String,

	@field:SerializedName("count")
	val count: Int
)

data class LocationResultsItem(

	@field:SerializedName("created")
	val created: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("residents")
	val residents: List<String>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("dimension")
	val dimension: String,

	@field:SerializedName("url")
	val url: String
)
