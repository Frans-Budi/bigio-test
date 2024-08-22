package com.fransbudikashira.core.util

sealed class StatusCharacter(val status: String) {
    object Alive: StatusCharacter("Alive")
    object Unknown: StatusCharacter("Unknown")
    object Dead: StatusCharacter("Dead")
}