package com.massana2110.pokeapp.mapper

import com.massana2110.pokeapp.core.domain.model.PokemonDetail
import com.massana2110.pokeapp.model.PokemonDetailUiModel
import com.massana2110.pokeapp.model.PokemonStatUiModel
import com.massana2110.pokeapp.model.PokemonTypeUiModel

fun PokemonDetail.toUiModel() = PokemonDetailUiModel(
    id = id,
    name = name.replaceFirstChar { it.uppercase() },
    imageUrl = spriteUrl,
    height = "%.1f m".format(height / 10.0),
    weight = "%.1f kg".format(weight / 10.0),
    baseExperience = baseExperience,
    description = description,
    types = types.map { PokemonTypeUiModel.fromCode(it) },
    stats = stats.map { PokemonStatUiModel(name = it.name, value = it.value) },
    abilities = abilities.map { it.replaceFirstChar { c -> c.uppercase() } }
)
