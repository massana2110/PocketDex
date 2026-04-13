package com.massana2110.pokeapp.ui.components.image

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

/**
 * A composable that displays an image from a remote URL.
 * @param modifier The modifier to be applied to the image.
 * @param scale The scale to apply to the image.
 * @param imageUrl The URL of the image to display.
 */
@Composable
fun PocketDexRemoteImage(
    modifier: Modifier = Modifier,
    scale: ContentScale = ContentScale.Fit,
    imageUrl: String
) {
    AsyncImage(
        modifier = modifier,
        model = imageUrl,
        contentScale = scale,
        contentDescription = null
    )
}
