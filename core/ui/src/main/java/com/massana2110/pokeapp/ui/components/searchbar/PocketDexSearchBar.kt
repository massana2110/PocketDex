package com.massana2110.pokeapp.ui.components.searchbar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.massana2110.pokeapp.core.ui.R
import com.massana2110.pokeapp.ui.theme.PocketDexTheme
import com.massana2110.pokeapp.ui.theme.gray40
import com.massana2110.pokeapp.ui.theme.grayAA
import com.massana2110.pokeapp.ui.theme.yellow

/**
 * Custom search bar for the PocketDex app.
 * @param modifier Modifier to be applied to the search bar.
 * @param query The current search query.
 * @param onQueryChange A callback that is triggered when the search query changes.
 * @param onSearch A callback that is triggered when the user submits the search query.
 */
@Composable
fun PocketDexSearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = stringResource(R.string.searchbar_placeholder),
                style = MaterialTheme.typography.labelSmall,
                color = gray40
            )
        },
        trailingIcon = {
            SearchIconButton {
                onSearch(query)
            }
        },
        textStyle = MaterialTheme.typography.labelSmall,
        singleLine = true,
        shape = MaterialTheme.shapes.extraLarge,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(query)
                keyboardController?.hide()
            }
        ),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = grayAA,
            focusedBorderColor = grayAA
        )
    )
}

@Composable
private fun SearchIconButton(
    modifier: Modifier = Modifier,
    onClickButton: () -> Unit
) {
    IconButton(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
        colors = IconButtonDefaults.iconButtonColors(containerColor = yellow),
        onClick = { onClickButton() }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_search),
            contentDescription = stringResource(R.string.searchbar_placeholder),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF8F8F8)
@Composable
private fun PocketDexSearchBarEmptyPreview() {
    PocketDexTheme {
        PocketDexSearchBar(
            query = "",
            onQueryChange = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF8F8F8)
@Composable
private fun PocketDexSearchBarWithTextPreview() {
    PocketDexTheme {
        PocketDexSearchBar(
            query = "Charizard",
            onQueryChange = {}
        )
    }
}
