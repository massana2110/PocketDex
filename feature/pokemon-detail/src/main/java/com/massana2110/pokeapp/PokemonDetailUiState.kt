package com.massana2110.pokeapp

import com.massana2110.pokeapp.model.PokemonDetailUiModel

data class PokemonDetailUiState(
    val isLoading: Boolean = false,
    val pokemon: PokemonDetailUiModel? = null,
    val errorMessage: String? = null
)
