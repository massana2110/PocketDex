package com.massana2110.pokeapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.massana2110.pokeapp.components.PokemonListItem
import com.massana2110.pokeapp.feature.pokemonlist.R
import com.massana2110.pokeapp.model.PokemonUiModel
import com.massana2110.pokeapp.ui.components.container.PocketDexScreenContainer
import com.massana2110.pokeapp.ui.components.searchbar.PocketDexSearchBar
import com.massana2110.pokeapp.ui.components.spacer.Spacer16
import com.massana2110.pokeapp.ui.theme.PocketDexTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    PokemonListScreenContent(
        uiState = uiState,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun PokemonListScreenContent(
    uiState: PokemonListUiState,
    onEvent: (PokemonListUiEvent) -> Unit = {}
) {
    PocketDexScreenContainer { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(all = 16.dp)
        ) {
            WelcomeText()
            Spacer16()
            PocketDexSearchBar(
                query = uiState.query,
                onQueryChange = {
                    onEvent(PokemonListUiEvent.OnSearchQueryChange(it))
                },
                onSearch = {}
            )
            Spacer16()
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(uiState.pokemonList) { pokemon ->
                    PokemonListItem(
                        pokemonId = pokemon.id,
                        pokemonName = pokemon.name,
                        pokemonImageUrl = pokemon.imageUrl,
                        onClickPokemon = {}
                    )
                }
            }
        }
    }
}

@Composable
private fun WelcomeText() {
    val boldStyle = MaterialTheme.typography.titleLarge
    val welcomeText = buildAnnotatedString {
        append(stringResource(R.string.pokemon_list_hello_label))
        append(" ")
        withStyle(boldStyle.toSpanStyle()) {
            append(stringResource(R.string.pokemon_list_welcome_label))
        }
    }
    Text(
        text = welcomeText,
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.primary
    )
}

@Preview
@Composable
private fun PokemonListScreenPreview() {
    PocketDexTheme {
        val dummyListPokemon = listOf("Pikachu", "Bulbasaur", "Charmander", "Squirtle")

        PokemonListScreenContent(
            uiState = PokemonListUiState(
                pokemonList = dummyListPokemon.mapIndexed { index, pokemon ->
                    PokemonUiModel(
                        id = index,
                        name = pokemon,
                        imageUrl = ""
                    )
                }
            )
        )
    }
}
