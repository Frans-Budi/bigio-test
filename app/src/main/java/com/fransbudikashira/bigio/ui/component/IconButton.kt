package com.fransbudikashira.bigio.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.fransbudikashira.bigio.ui.theme.BigioTheme

@Composable
fun IconButton(
    onClick: (Boolean) -> Unit,
    isToggle: Boolean,
    iconOff: ImageVector,
    iconOn: ImageVector,
    modifier: Modifier
) {
    val icon = if (isToggle) iconOn else iconOff
    IconButton(
        onClick = { onClick(!isToggle) },
        modifier = modifier
    ) {
        Icon(imageVector = icon, contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
private fun IconButtonPreview() {
    BigioTheme {
        IconButton(
            onClick = {},
            iconOff = Icons.Default.FavoriteBorder,
            iconOn = Icons.Default.Favorite,
            isToggle = false,
            modifier = Modifier
        )
    }
}