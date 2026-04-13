package com.massana2110.pokeapp.core.domain.repository

import com.massana2110.pokeapp.core.domain.model.Pokemon
import com.massana2110.pokeapp.core.domain.model.PokemonDetail

interface PokemonRepository {
    suspend fun getPokemonGeneration1(): Result<List<Pokemon>>
    suspend fun searchPokemon(nameOrId: String): Result<Pokemon>
    suspend fun getPokemonDetail(id: Int): Result<PokemonDetail>
}
