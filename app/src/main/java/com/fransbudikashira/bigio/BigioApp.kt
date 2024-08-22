package com.fransbudikashira.bigio

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fransbudikashira.bigio.ui.component.DetailTopAppBar
import com.fransbudikashira.bigio.ui.component.FloatingSearchButton
import com.fransbudikashira.bigio.ui.component.MyBottomAppBar
import com.fransbudikashira.bigio.ui.component.MyTopAppBar
import com.fransbudikashira.bigio.ui.navigation.Screen
import com.fransbudikashira.bigio.ui.screen.character.CharacterScreen
import com.fransbudikashira.bigio.ui.screen.character.detail.DetailCharacter
import com.fransbudikashira.bigio.ui.screen.episode.EpisodeScreen
import com.fransbudikashira.bigio.ui.screen.episode.detail.DetailEpisode
import com.fransbudikashira.bigio.ui.screen.location.LocationScreen
import com.fransbudikashira.bigio.ui.screen.location.detail.DetailLocation
import com.fransbudikashira.bigio.ui.screen.search.SearchScreen
import com.fransbudikashira.bigio.ui.screen.setting.SettingScreen
import com.fransbudikashira.bigio.ui.screen.splash.SplashScreen

@Composable
fun BigioApp(
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            when(currentRoute) {
                Screen.Character.route -> {
                    MyTopAppBar(title = stringResource(R.string.menu_character))
                }
                Screen.Episode.route -> {
                    MyTopAppBar(title = stringResource(R.string.menu_episode))
                }
                Screen.Location.route -> {
                    MyTopAppBar(title = stringResource(R.string.menu_location))
                }
                Screen.Setting.route -> {
                    MyTopAppBar(title = stringResource(R.string.menu_setting))
                }
                Screen.DetailCharacter.route -> {
                    DetailTopAppBar(
                        title = stringResource(R.string.character_detail),
                        onBackClick = { navController.navigateUp() }
                    )
                }
                Screen.DetailEpisode.route -> {
                    DetailTopAppBar(
                        title = stringResource(R.string.episode_detail),
                        onBackClick = { navController.navigateUp() }
                    )
                }
                Screen.DetailLocation.route -> {
                    DetailTopAppBar(
                        title = stringResource(R.string.location_detail),
                        onBackClick = { navController.navigateUp() }
                    )
                }
            }
        },
        bottomBar = {
            if (currentRoute == Screen.Character.route ||
                currentRoute == Screen.Episode.route ||
                currentRoute == Screen.Location.route ||
                currentRoute == Screen.Setting.route) {
                MyBottomAppBar(navController)
            }
        },
        floatingActionButton = {
            if (currentRoute == Screen.Character.route)
                FloatingSearchButton(
                    onClick = {
                        navController.navigate(
                            Screen.Search.route
                        )
                    },
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .padding(bottom = 16.dp)
                )
        },
        modifier = Modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(navController)
            }
            composable(Screen.Character.route) {
                CharacterScreen(
                    onClick = { characterId ->
                        navController.navigate(
                            Screen.DetailCharacter.createRoute(characterId)
                        )
                    }
                )
            }
            composable(
                route = Screen.DetailCharacter.route,
                arguments = listOf(navArgument("characterId") { type = NavType.StringType })
            ) {
                val characterId = it.arguments?.getString("characterId") ?: ""
                DetailCharacter(characterId)
            }
            composable(Screen.Episode.route) {
                EpisodeScreen(
                    onClick = { episodeId ->
                        navController.navigate(
                            Screen.DetailEpisode.createRoute(episodeId)
                        )
                    }
                )
            }
            composable(
                route = Screen.DetailEpisode.route,
                arguments = listOf(navArgument("episodeId") { type = NavType.StringType})
            ) {
                val episodeId = it.arguments?.getString("episodeId") ?: ""
                DetailEpisode(episodeId = episodeId)
            }
            composable(Screen.Location.route) {
                LocationScreen(
                    onClick = { locationId ->
                        navController.navigate(
                            Screen.DetailLocation.createRoute(locationId)
                        )
                    }
                )
            }
            composable(
                route = Screen.DetailLocation.route,
                arguments = listOf(navArgument("locationId") { type = NavType.StringType})
            ) {
                val locationId = it.arguments?.getString("locationId") ?: ""
                DetailLocation(locationId = locationId)
            }
            composable(Screen.Setting.route) {
                SettingScreen()
            }
            composable(Screen.Search.route) {
                SearchScreen(
                    onClick = { characterId ->
                        navController.navigate(
                            Screen.DetailCharacter.createRoute(characterId)
                        )
                    }
                )
            }
        }
    }
}