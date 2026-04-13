package com.massana2110.pokeapp.ui.components.appbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.massana2110.pokeapp.core.ui.R
import com.massana2110.pokeapp.ui.theme.PocketDexTheme
import com.massana2110.pokeapp.ui.theme.gray7c

/**
 * A custom top app bar for the PocketDex app.
 * @param modifier Modifier to be applied to the top app bar.
 * @param title The title to be displayed in the top app bar, if null, the logo will be displayed.
 * @param actionText The action text to be displayed in the top app bar.
 * @param showBackButton Whether to show the back button or not.
 * @param onBackClick The action to perform when the back button is clicked.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PocketDexTopBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    actionText: String? = null,
    showBackButton: Boolean = false,
    onBackClick: () -> Unit = {}
) {
    TopAppBar(
        modifier = modifier,
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(R.drawable.ic_left_arrow),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = null
                    )
                }
            }
        },
        title = { PocketDexTopBarTitle(title) },
        actions = {
            actionText?.let { text ->
                Text(
                    modifier = Modifier.padding(end = 16.dp),
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = gray7c
                )
            }
        }
    )
}

@Composable
private fun PocketDexTopBarTitle(title: String?) {
    title?.let { text ->
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    } ?: Image(
        painter = painterResource(R.drawable.pokedex_logo),
        contentDescription = null
    )
}

@Preview
@Composable
private fun PocketDexTopBarImagePreview() {
    PocketDexTheme {
        PocketDexTopBar()
    }
}

@Preview
@Composable
private fun PocketDexTopBarTextPreview() {
    PocketDexTheme {
        PocketDexTopBar(
            title = "Charizard",
            actionText = "#0006",
            showBackButton = true
        )
    }
}
