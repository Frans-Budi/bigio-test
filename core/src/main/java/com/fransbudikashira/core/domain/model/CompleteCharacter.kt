package com.fransbudikashira.core.domain.model

import com.fransbudikashira.core.util.Gender
import com.fransbudikashira.core.util.StatusCharacter

data class CompleteCharacter(
    val id: String,
    val imageUrl: String,
    val name: String,
    val species: String,
    val statusCharacter: StatusCharacter,
    val gender: Gender,
    val origin: String,
    val location: String,
    val episodes: List<Episode>
)
