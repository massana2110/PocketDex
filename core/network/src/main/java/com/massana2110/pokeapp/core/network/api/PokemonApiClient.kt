package com.massana2110.pokeapp.core.network.api

import com.massana2110.pokeapp.core.network.model.GenerationResponseDTO
import com.massana2110.pokeapp.core.network.model.PokemonDetailResponseDTO
import com.massana2110.pokeapp.core.network.model.PokemonSearchResponseDTO
import com.massana2110.pokeapp.core.network.model.PokemonSpeciesResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiClient {

    @GET("generation/1")
    suspend fun getPokemonGeneration1(): Response<GenerationResponseDTO>

    @GET("pokemon/{nameOrId}")
    suspend fun searchPokemon(@Path("nameOrId") nameOrId: String): Response<PokemonSearchResponseDTO>

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: Int): Response<PokemonDetailResponseDTO>

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(@Path("id") id: Int): Response<PokemonSpeciesResponseDTO>
}
