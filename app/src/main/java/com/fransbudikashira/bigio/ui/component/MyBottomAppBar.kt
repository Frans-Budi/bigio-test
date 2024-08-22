package com.fransbudikashira.bigio.ui.component

import androidx.compose.foundation.border
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.fransbudikashira.bigio.R
import com.fransbudikashira.bigio.ui.navigation.NavigationItem
import com.fransbudikashira.bigio.ui.navigation.Screen
import com.fransbudikashira.bigio.ui.theme.poppinsFontFamily

@Composable
fun MyBottomAppBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
            .border(1.5.dp, MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f))
    ) {
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_character),
                icon = Icons.Default.AccountBox,
                screen = Screen.Character
            ),
            NavigationItem(
                title = stringResource(R.string.menu_episode),
                icon = Icons.Default.Menu,
                screen = Screen.Episode
            ),
            NavigationItem(
                title = stringResource(R.string.menu_location),
                icon = Icons.Default.LocationOn,
                screen = Screen.Location
            ),
            NavigationItem(
                title = stringResource(R.string.menu_setting),
                icon = Icons.Default.Settings,
                screen = Screen.Setting
            )
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontFamily = poppinsFontFamily,
                        fontSize = 11.sp,
                        fontWeight = if (currentRoute == item.screen.route) FontWeight.SemiBold else FontWeight.Normal
                    )
                },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
            )
        }
    }
}