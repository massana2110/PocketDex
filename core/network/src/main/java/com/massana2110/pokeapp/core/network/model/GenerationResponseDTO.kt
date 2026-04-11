package com.massana2110.pokeapp.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationResponseDTO(
    val id: Int,
    val name: String,
    @SerialName("pokemon_species")
    val pokemonSpecies: List<NamedApiResourceDTO>
)

@Serializable
data class NamedApiResourceDTO(
    val name: String,
    val url: String
)
