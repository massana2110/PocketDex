package com.massana2110.pokeapp.core.data.mapper

import com.massana2110.pokeapp.core.domain.model.Pokemon
import com.massana2110.pokeapp.core.domain.model.PokemonDetail
import com.massana2110.pokeapp.core.domain.model.PokemonStat
import com.massana2110.pokeapp.core.network.model.GenerationResponseDTO
import com.massana2110.pokeapp.core.network.model.NamedApiResourceDTO
import com.massana2110.pokeapp.core.network.model.PokemonDetailResponseDTO
import com.massana2110.pokeapp.core.network.model.PokemonSearchResponseDTO
import com.massana2110.pokeapp.core.network.model.PokemonSpeciesResponseDTO

private const val ZERO = 0
private const val LANG_ES = "es"
private const val LANG_EN = "en"

fun GenerationResponseDTO.toDomain(): List<Pokemon> =
    pokemonSpecies.map { it.toDomain() }

fun NamedApiResourceDTO.toDomain(): Pokemon {
    val id = url.trimEnd('/').substringAfterLast('/').toInt()
    return Pokemon(id = id, name = name)
}

fun PokemonSearchResponseDTO.toDomain() = Pokemon(id = id, name = name)

fun PokemonSpeciesResponseDTO.toDescription(): String =
    (flavorTextEntries.firstOrNull { it.language.name == LANG_ES }
        ?: flavorTextEntries.firstOrNull { it.language.name == LANG_EN })
        ?.flavorText
        ?.replace("\n", " ")
        ?.replace("\u000c", " ")
        .orEmpty()

fun PokemonDetailResponseDTO.toDomain(description: String) = PokemonDetail(
    id = id ?: ZERO,
    name = name.orEmpty(),
    height = height ?: ZERO,
    weight = weight ?: ZERO,
    baseExperience = baseExperience,
    spriteUrl = sprites?.other?.home?.frontDefault.orEmpty(),
    description = description,
    types = types?.map { it.type.name }.orEmpty(),
    stats = stats?.map { PokemonStat(name = it.stat.name, value = it.baseStat) }.orEmpty(),
    abilities = abilities?.map { it.ability.name }.orEmpty()
)
