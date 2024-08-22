package com.fransbudikashira.bigio.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fransbudikashira.bigio.ui.theme.BigioTheme
import com.fransbudikashira.core.util.StatusCharacter

@Composable
fun StatusIndicator(
    statusCharacter: StatusCharacter,
    modifier: Modifier = Modifier
) {
    val color = when (statusCharacter) {
        StatusCharacter.Alive -> Color.Green
        StatusCharacter.Dead -> Color.Red
        StatusCharacter.Unknown -> Color.Gray
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .background(color, CircleShape)
        )
        Spacer(modifier = Modifier.size(6.dp))
        Text(
            text = statusCharacter.status,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StatusIndicatorPreview() {
    BigioTheme {
        StatusIndicator(
            statusCharacter = StatusCharacter.Alive
        )
    }
}