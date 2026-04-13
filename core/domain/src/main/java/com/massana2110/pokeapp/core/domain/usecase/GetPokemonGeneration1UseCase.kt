package com.massana2110.pokeapp.core.domain.usecase

import com.massana2110.pokeapp.core.domain.model.Pokemon
import com.massana2110.pokeapp.core.domain.repository.PokemonRepository

class GetPokemonGeneration1UseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(): Result<List<Pokemon>> = repository.getPokemonGeneration1()
}
