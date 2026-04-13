package com.massana2110.pokeapp.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonSpeciesResponseDTO(
    @SerialName("flavor_text_entries") val flavorTextEntries: List<FlavorTextEntryDTO>
)

@Serializable
data class FlavorTextEntryDTO(
    @SerialName("flavor_text") val flavorText: String,
    val language: NamedApiResourceDTO
)
