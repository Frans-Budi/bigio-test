package com.fransbudikashira.bigio.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.fransbudikashira.bigio.ui.theme.BigioTheme

@Composable
fun EpisodeItem(
    title: String,
    episode: String,
    airDate: String,
    isFavorite: Boolean,
    onClick: () -> Unit,
    onFavoriteClick: (Boolean) -> Unit,
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical =  10.dp)
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = episode,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.size(6.dp))
                Text(
                    text = airDate,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            IconButton(
                onClick = onFavoriteClick,
                isToggle = isFavorite,
                iconOff = Icons.Default.FavoriteBorder,
                iconOn = Icons.Default.Favorite,
                modifier = Modifier
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun EpisodeItemPreview() {
    BigioTheme {
        EpisodeItem(
            title = "Pilot",
            episode = "S01E01",
            airDate = "December 2, 2013",
            isFavorite = false,
            onClick = {},
            onFavoriteClick = {},
            modifier = Modifier
        )
    }
}