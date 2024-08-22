package com.fransbudikashira.bigio.ui.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.fransbudikashira.bigio.ui.theme.BigioTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        modifier = modifier
    )
}

@PreviewLightDark
@Composable
fun MyTopAppBarPreview() {
    BigioTheme {
        MyTopAppBar(title = "Characters")
    }
}