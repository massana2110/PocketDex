package com.massana2110.pokeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.massana2110.pokeapp.core.domain.usecase.GetPokemonDetailUseCase
import com.massana2110.pokeapp.mapper.toUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val pokemonId: Int,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PokemonDetailUiState())
    val uiState: StateFlow<PokemonDetailUiState> = _uiState.asStateFlow()

    init {
        getPokemonDetail()
    }

    private fun getPokemonDetail() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            getPokemonDetailUseCase(pokemonId)
                .onSuccess { pokemonDetail ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            pokemon = pokemonDetail.toUiModel()
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = "Error al obtener los detalles del Pokémon: ${error.message}"
                        )
                    }
                }
        }
    }
}
