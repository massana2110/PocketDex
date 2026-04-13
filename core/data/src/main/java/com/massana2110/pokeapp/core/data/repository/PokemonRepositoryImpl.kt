package com.massana2110.pokeapp.core.data.repository

import com.massana2110.pokeapp.core.data.mapper.toDescription
import com.massana2110.pokeapp.core.data.mapper.toDomain
import com.massana2110.pokeapp.core.data.util.networkCall
import com.massana2110.pokeapp.core.domain.model.Pokemon
import com.massana2110.pokeapp.core.domain.model.PokemonDetail
import com.massana2110.pokeapp.core.domain.repository.PokemonRepository
import com.massana2110.pokeapp.core.network.api.PokemonApiClient
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class PokemonRepositoryImpl(
    private val apiClient: PokemonApiClient
) : PokemonRepository {

    override suspend fun getPokemonGeneration1(): Result<List<Pokemon>> =
        networkCall { apiClient.getPokemonGeneration1() }.map { it.toDomain() }

    override suspend fun searchPokemon(nameOrId: String): Result<Pokemon> =
        networkCall { apiClient.searchPokemon(nameOrId) }.map { it.toDomain() }

    override suspend fun getPokemonDetail(id: Int): Result<PokemonDetail> = coroutineScope {
        val detailDeferred = async { networkCall { apiClient.getPokemonDetail(id) } }
        val speciesDeferred = async { networkCall { apiClient.getPokemonSpecies(id) } }

        val detailResult = detailDeferred.await()
        val description = speciesDeferred.await().getOrNull()?.toDescription().orEmpty()

        detailResult.map { it.toDomain(description) }
    }
}
