package com.massana2110.pokeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.massana2110.pokeapp.core.domain.usecase.GetPokemonGeneration1UseCase
import com.massana2110.pokeapp.mapper.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val getPokemonGeneration1UseCase: GetPokemonGeneration1UseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonListUiState())
    val uiState: StateFlow<PokemonListUiState> = _uiState.asStateFlow()

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getPokemonGeneration1UseCase()
                .onSuccess { pokemonList ->
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            pokemonList = pokemonList.map { it.toUiModel() }
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = error.message
                        )
                    }
                }
        }
    }

    private fun onSearchQueryChange(query: String) {
        _uiState.update {
            it.copy(query = query)
        }
    }

    fun onEvent(event: PokemonListUiEvent) {
        when (event) {
            is PokemonListUiEvent.OnClickPokemon -> {}
            is PokemonListUiEvent.OnSearchQueryChange -> onSearchQueryChange(event.query)
            PokemonListUiEvent.OnClickSearchButton -> {}
        }
    }
}
