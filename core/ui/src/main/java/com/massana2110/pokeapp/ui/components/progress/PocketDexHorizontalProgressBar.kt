package com.massana2110.pokeapp.ui.components.progress

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.massana2110.pokeapp.ui.theme.PocketDexTheme
import com.massana2110.pokeapp.ui.theme.fire

/**
 * A composable that displays a custom horizontal progress bar.
 * @param progress The progress of the bar.
 * @param trackColor The color of the track.
 * @param indicatorColor The color of the indicator.
 */
@Composable
fun PocketDexHorizontalProgressBar(
    modifier: Modifier = Modifier,
    progress: Float,
    trackColor: Color,
    indicatorColor: Color,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(12.dp)
            .clip(CircleShape)
            .background(trackColor)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress.coerceIn(0f, 1f))
                .fillMaxHeight()
                .clip(CircleShape)
                .background(indicatorColor)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PocketDexHorizontalProgressBarPreview() {
    PocketDexTheme {
        PocketDexHorizontalProgressBar(
            progress = 0.6f,
            trackColor = fire.copy(alpha = 0.2f),
            indicatorColor = fire
        )
    }
}
