package com.massana2110.pokeapp.core.domain.model

data class PokemonDetail(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val baseExperience: Int?,
    val spriteUrl: String,
    val description: String,
    val types: List<String>,
    val stats: List<PokemonStat>,
    val abilities: List<String>
)

data class PokemonStat(
    val name: String,
    val value: Int
)
