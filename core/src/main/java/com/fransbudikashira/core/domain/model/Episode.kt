package com.fransbudikashira.core.domain.model

data class Episode(
    val id: String,
    val title: String,
    val episode: String,
    val airDate: String,
    val isFavorite: Boolean
)