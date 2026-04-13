package com.massana2110.pokeapp.ui.components.container

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.massana2110.pokeapp.ui.components.appbar.PocketDexTopBar
import com.massana2110.pokeapp.ui.theme.PocketDexTheme

/**
 * Base PocketDex screen container that provides the common elements for all screens.
 * @param title The title to be displayed in the top app bar, if null, the logo will be displayed.
 * @param actionText The action text to be displayed in the top app bar.
 * @param showBackButton Whether to show the back button or not.
 * @param onTopBarBackClick The action to perform when the back button is clicked.
 * @param content The content to be displayed inside the screen
 */
@Composable
fun PocketDexScreenContainer(
    title: String? = null,
    actionText: String? = null,
    showBackButton: Boolean = false,
    onTopBarBackClick: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            PocketDexTopBar(
                title = title,
                actionText = actionText,
                showBackButton = showBackButton,
                onBackClick = onTopBarBackClick
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
        content = content
    )
}

@Preview(showSystemUi = false)
@Composable
private fun PocketDexScreenContainerPreview() {
    PocketDexTheme {
        PocketDexScreenContainer { paddingValues ->
            Box(
                modifier = Modifier.fillMaxSize().padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "PocketDex container",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}
