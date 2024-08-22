package com.fransbudikashira.bigio.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fransbudikashira.bigio.R
import com.fransbudikashira.bigio.ui.navigation.Screen
import com.fransbudikashira.bigio.ui.theme.BigioTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(key1 = true) {
        delay(2000L)
        navController.navigate(Screen.Character.route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }
    SplashContent(modifier)
}

@Composable
fun SplashContent(
    modifier: Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.lg_bigio),
                contentDescription = null,
                modifier.size(160.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    BigioTheme {
        SplashContent(modifier = Modifier)
    }
}