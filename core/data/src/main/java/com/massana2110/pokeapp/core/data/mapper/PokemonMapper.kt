package com.massana2110.pokeapp.core.data.mapper

import com.massana2110.pokeapp.core.domain.model.Pokemon
import com.massana2110.pokeapp.core.network.model.GenerationResponseDTO
import com.massana2110.pokeapp.core.network.model.NamedApiResourceDTO

fun GenerationResponseDTO.toDomain(): List<Pokemon> =
    pokemonSpecies.map { it.toDomain() }

fun NamedApiResourceDTO.toDomain(): Pokemon {
    val id = url.trimEnd('/').substringAfterLast('/').toInt()
    return Pokemon(id = id, name = name)
}
