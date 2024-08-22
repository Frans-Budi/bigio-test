package com.fransbudikashira.core.domain.model

data class CompleteEpisode(
    val id: String,
    val title: String,
    val episode: String,
    val airDate: String,
    val characters: List<Character>
)