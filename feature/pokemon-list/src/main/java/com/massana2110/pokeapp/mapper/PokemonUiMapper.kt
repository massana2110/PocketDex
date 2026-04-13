package com.massana2110.pokeapp.mapper

import com.massana2110.pokeapp.core.domain.model.Pokemon
import com.massana2110.pokeapp.model.PokemonUiModel

private const val POKEMON_IMAGE_URL =
    "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/%d.png"

fun Pokemon.toUiModel() = PokemonUiModel(
    id = id,
    name = name.replaceFirstChar { it.uppercase() },
    imageUrl = POKEMON_IMAGE_URL.format(id)
)
