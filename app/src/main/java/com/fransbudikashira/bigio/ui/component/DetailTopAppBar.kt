package com.fransbudikashira.bigio.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.fransbudikashira.bigio.ui.theme.BigioTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopAppBar(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shadowElevation = 8.dp
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            modifier = modifier
        )
    }
}

@PreviewLightDark
@Composable
private fun DetailTopAppBarPreview() {
    BigioTheme {
        DetailTopAppBar(
            title = "Detail Characters",
            onBackClick = {}
        )
    }
}