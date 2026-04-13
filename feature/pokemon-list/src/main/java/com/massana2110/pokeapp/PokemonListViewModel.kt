package com.massana2110.pokeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.massana2110.pokeapp.core.domain.usecase.GetPokemonGeneration1UseCase
import com.massana2110.pokeapp.core.domain.usecase.SearchPokemonUseCase
import com.massana2110.pokeapp.mapper.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val getPokemonGeneration1UseCase: GetPokemonGeneration1UseCase,
    private val searchPokemonUseCase: SearchPokemonUseCase
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

    private fun searchPokemon() {
        val query = _uiState.value.query.trim()
        if (query.isEmpty()) return

        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    searchResult = null,
                    errorMessage = null
                )
            }
            searchPokemonUseCase(query)
                .onSuccess { pokemon ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            searchResult = pokemon.toUiModel()
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
            it.copy(
                query = query,
                searchResult = null,
                errorMessage = null
            )
        }
    }

    fun onEvent(event: PokemonListUiEvent) {
        when (event) {
            is PokemonListUiEvent.OnClickPokemon -> {}
            is PokemonListUiEvent.OnSearchQueryChange -> onSearchQueryChange(event.query)
            PokemonListUiEvent.OnClickSearchButton -> searchPokemon()
        }
    }
}
