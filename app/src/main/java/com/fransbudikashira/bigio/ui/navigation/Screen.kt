package com.fransbudikashira.bigio.ui.navigation

sealed class Screen(val route: String) {
    object Splash: Screen("splash")
    object Character: Screen("character")
    object DetailCharacter: Screen("character/{characterId}") {
        fun createRoute(characterId: String) = "character/$characterId"
    }
    object Episode: Screen("episode")
    object DetailEpisode: Screen("episode/{episodeId}") {
        fun createRoute(episodeId: String) = "episode/$episodeId"
    }
    object Location: Screen("location")
    object DetailLocation: Screen("location/{locationId}") {
        fun createRoute(locationId: String) = "location/$locationId"
    }
    object Search: Screen("search")
    object Setting: Screen("setting")
}