package com.massana2110.pokeapp.core.network.api

import com.massana2110.pokeapp.core.network.model.GenerationResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApiClient {

    @GET("generation/1")
    suspend fun getPokemonGeneration1(): Response<GenerationResponseDTO>
}
