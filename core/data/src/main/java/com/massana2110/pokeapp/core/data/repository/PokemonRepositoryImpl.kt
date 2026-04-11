package com.massana2110.pokeapp.core.data.repository

import com.massana2110.pokeapp.core.data.mapper.toDomain
import com.massana2110.pokeapp.core.data.util.networkCall
import com.massana2110.pokeapp.core.domain.model.Pokemon
import com.massana2110.pokeapp.core.domain.repository.PokemonRepository
import com.massana2110.pokeapp.core.network.api.PokemonApiClient

class PokemonRepositoryImpl(
    private val apiClient: PokemonApiClient
) : PokemonRepository {

    override suspend fun getPokemonGeneration1(): Result<List<Pokemon>> =
        networkCall { apiClient.getPokemonGeneration1() }.map { it.toDomain() }
}
