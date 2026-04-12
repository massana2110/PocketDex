package com.massana2110.pokeapp.core.domain.usecase

import com.massana2110.pokeapp.core.domain.exception.ApiException
import com.massana2110.pokeapp.core.domain.model.Pokemon
import com.massana2110.pokeapp.core.domain.repository.PokemonRepository

private const val MAX_GENERATION_1_ID = 151
private const val NOT_FOUND_ERROR_CODE = 404

class SearchPokemonUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(nameOrId: String): Result<Pokemon> =
        repository.searchPokemon(nameOrId.trim().lowercase())
            .recoverCatching { error ->
                if (error is ApiException && error.code == NOT_FOUND_ERROR_CODE) {
                    error("No se encontró ningún Pokémon con el nombre o ID '$nameOrId'. Intenta de nuevo")
                }
                throw error
            }
            .mapCatching { pokemon ->
                require(pokemon.id <= MAX_GENERATION_1_ID) {
                    "${pokemon.name.replaceFirstChar { it.uppercase() }} no pertenece a la generación 1. Intenta con otro pokemon"
                }
                pokemon
            }
}
