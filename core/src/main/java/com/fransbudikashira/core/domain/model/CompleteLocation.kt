package com.fransbudikashira.core.domain.model

data class CompleteLocation(
    val id: String,
    val name: String,
    val type: String,
    val dimension: String,
    val characters: List<Character>
)
