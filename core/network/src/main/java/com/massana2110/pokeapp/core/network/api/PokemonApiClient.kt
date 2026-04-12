package com.massana2110.pokeapp.core.network.api

import com.massana2110.pokeapp.core.network.model.GenerationResponseDTO
import com.massana2110.pokeapp.core.network.model.PokemonSearchResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiClient {

    @GET("generation/1")
    suspend fun getPokemonGeneration1(): Response<GenerationResponseDTO>

    @GET("pokemon/{nameOrId}")
    suspend fun searchPokemon(@Path("nameOrId") nameOrId: String): Response<PokemonSearchResponseDTO>
}
