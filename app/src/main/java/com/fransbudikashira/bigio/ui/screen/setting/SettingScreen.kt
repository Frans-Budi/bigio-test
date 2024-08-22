package com.fransbudikashira.bigio.ui.screen.setting

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fransbudikashira.bigio.ui.theme.BigioTheme

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier
) {
    SettingContent(modifier)
}

@Composable
fun SettingContent(
    modifier: Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "Setting")
    }
}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    BigioTheme {
        SettingContent(modifier = Modifier)
    }
}