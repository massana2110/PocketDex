package com.massana2110.pokeapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.massana2110.pokeapp.components.PokemonListItem
import com.massana2110.pokeapp.feature.pokemonlist.R
import com.massana2110.pokeapp.model.PokemonUiModel
import com.massana2110.pokeapp.ui.components.container.PocketDexScreenContainer
import com.massana2110.pokeapp.ui.components.loaders.CircularLoader
import com.massana2110.pokeapp.ui.components.searchbar.PocketDexSearchBar
import com.massana2110.pokeapp.ui.components.spacer.Spacer16
import com.massana2110.pokeapp.ui.theme.PocketDexTheme
import com.massana2110.pokeapp.ui.theme.grayAA
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
    val displayedList by remember(uiState) {
        derivedStateOf {
            if (uiState.searchResult != null) listOf(uiState.searchResult) else uiState.pokemonList
        }
    }

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
                onQueryChange = { onEvent(PokemonListUiEvent.OnSearchQueryChange(it)) },
                onSearch = { onEvent(PokemonListUiEvent.OnClickSearchButton) }
            )
            Spacer16()
            when {
                uiState.isLoading -> {
                    CircularLoader(
                        modifier = Modifier.fillMaxSize()
                    )
                }
                uiState.errorMessage != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = uiState.errorMessage,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.error,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                else -> {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        uiState.searchResult?.let {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                Text(
                                    modifier = Modifier
                                        .animateItem()
                                        .fillMaxWidth(),
                                    text = stringResource(R.string.pokemon_list_search_result_label),
                                    style = MaterialTheme.typography.titleSmall,
                                    color = grayAA
                                )
                            }
                        }
                        items(displayedList, key = { it.id }) { pokemon ->
                            PokemonListItem(
                                modifier = Modifier.animateItem(),
                                pokemonId = pokemon.id,
                                pokemonName = pokemon.name,
                                pokemonImageUrl = pokemon.imageUrl,
                                onClickPokemon = { onEvent(PokemonListUiEvent.OnClickPokemon(it)) }
                            )
                        }
                    }
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
