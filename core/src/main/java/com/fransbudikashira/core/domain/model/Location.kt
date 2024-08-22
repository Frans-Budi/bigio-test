package com.fransbudikashira.core.domain.model

data class Location(
    val id: String,
    val name: String,
    val type: String,
    val dimension: String,
    val isFavorite: Boolean
)