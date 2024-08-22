package com.fransbudikashira.bigio.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.fransbudikashira.bigio.R

@Composable
fun NetworkImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier
) {
    AsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.unknown_person),
        error = painterResource(R.drawable.unknown_person),
        modifier = modifier
    )
}