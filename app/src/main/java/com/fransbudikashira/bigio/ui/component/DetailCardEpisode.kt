package com.fransbudikashira.bigio.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fransbudikashira.core.domain.model.DetailItemData

@Composable
fun DetailCardEpisode(
    listDetail: List<DetailItemData>,
    onClick: (Int) -> Unit,
    modifier: Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(modifier = modifier) {
            listDetail.forEachIndexed { index, item ->
                DetailItemEpisode(
                    label = item.label,
                    text = item.text,
                    clickable = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onClick(index)
                        }
                )
                if (index < listDetail.size - 1)
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2f),
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
            }
        }
    }
}