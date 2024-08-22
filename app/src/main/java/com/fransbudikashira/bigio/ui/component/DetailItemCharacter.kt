package com.fransbudikashira.bigio.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DetailItemCharacter(
    imageUrl: String,
    name: String,
    clickable: Boolean = false,
    modifier: Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Card(
            shape = CircleShape,
            modifier = Modifier
        ) {
            NetworkImage(
                imageUrl = imageUrl,
                contentDescription = null,
                modifier = Modifier.size(36.dp)
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(modifier = Modifier.weight(1f))
        if (clickable)
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(16.dp)
            )
    }
}