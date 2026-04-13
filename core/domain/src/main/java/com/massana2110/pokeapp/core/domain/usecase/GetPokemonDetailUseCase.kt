package com.massana2110.pokeapp.core.domain.usecase

import com.massana2110.pokeapp.core.domain.model.PokemonDetail
import com.massana2110.pokeapp.core.domain.repository.PokemonRepository

class GetPokemonDetailUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): Result<PokemonDetail> =
        repository.getPokemonDetail(id)
}
