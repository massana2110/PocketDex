package com.massana2110.pokeapp.ui.components.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage

@Composable
fun PocketDexRemoteImage(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    AsyncImage(
        modifier = modifier,
        model = imageUrl,
        contentDescription = null
    )
}
