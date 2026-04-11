package com.massana2110.pokeapp

sealed interface PokemonListUiEvent {
    data class OnClickPokemon(val pokemonId: Int) : PokemonListUiEvent
    data class OnSearchQueryChange(val query: String) : PokemonListUiEvent
    data object OnClickSearchButton : PokemonListUiEvent
}