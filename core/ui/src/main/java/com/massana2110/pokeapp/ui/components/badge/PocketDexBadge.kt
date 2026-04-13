package com.massana2110.pokeapp.ui.components.badge

import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.massana2110.pokeapp.core.ui.R
import com.massana2110.pokeapp.ui.theme.PocketDexTheme
import com.massana2110.pokeapp.ui.theme.fire

/**
 * A composable that displays a custom rounded material badge with an icon and a label.
 * @param icon The icon to display in the badge.
 * @param badgeColor The color of the badge.
 * @param label The label to display in the badge.
 * @param labelColor The color of the label.
 */
@Composable
fun PocketDexBadge(
    icon: Int? = null,
    labelColor: Color = Color.White,
    badgeColor: Color,
    label: String,
) {
    ElevatedSuggestionChip(
        onClick = {},
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = labelColor
            )
        },
        icon = {
            icon?.let {
                Image(
                    painter = painterResource(it),
                    contentDescription = null
                )
            }
        },
        elevation = SuggestionChipDefaults.elevatedSuggestionChipElevation(
            pressedElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp,
            elevation = 0.dp
        ),
        shape = CircleShape,
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = badgeColor,
        )
    )
}

@Preview
@Composable
private fun PocketDexBadgePreview() {
    PocketDexTheme {
        PocketDexBadge(
            badgeColor = fire,
            icon = R.drawable.ic_left_arrow,
            label = "Label",
            labelColor = MaterialTheme.colorScheme.background
        )
    }
}