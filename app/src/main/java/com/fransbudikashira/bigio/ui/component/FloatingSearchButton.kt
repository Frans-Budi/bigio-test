package com.fransbudikashira.bigio.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FloatingSearchButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FloatingActionButton(
        shape = FloatingActionButtonDefaults.largeShape,
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null
        )
    }
}