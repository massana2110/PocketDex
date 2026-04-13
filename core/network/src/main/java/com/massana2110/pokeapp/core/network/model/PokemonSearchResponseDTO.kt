package com.massana2110.pokeapp.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonSearchResponseDTO(
    val id: Int,
    val name: String
)
