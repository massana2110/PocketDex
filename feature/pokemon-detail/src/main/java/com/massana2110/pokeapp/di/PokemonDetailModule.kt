package com.massana2110.pokeapp.di

import com.massana2110.pokeapp.PokemonDetailViewModel
import com.massana2110.pokeapp.core.domain.usecase.GetPokemonDetailUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val pokemonDetailModule = module {
    factoryOf(::GetPokemonDetailUseCase)
    viewModel { params ->
        PokemonDetailViewModel(
            pokemonId = params.get(),
            getPokemonDetailUseCase = get()
        )
    }
}
