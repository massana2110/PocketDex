package com.massana2110.pokeapp

import com.massana2110.pokeapp.model.PokemonUiModel

data class PokemonListUiState(
    val isLoading: Boolean = false,
    val pokemonList: List<PokemonUiModel> = emptyList(),
    val query: String = "",
    val errorMessage: String? = null
)
