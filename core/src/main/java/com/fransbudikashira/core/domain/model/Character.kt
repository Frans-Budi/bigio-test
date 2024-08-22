package com.fransbudikashira.core.domain.model

import com.fransbudikashira.core.util.StatusCharacter

data class Character(
    val id: String,
    val imageUrl: String,
    val name: String,
    val species: String,
    val status: StatusCharacter,
    val isFavorite: Boolean
)