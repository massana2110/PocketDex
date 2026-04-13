package com.massana2110.pokeapp.di

import com.massana2110.pokeapp.PokemonListViewModel
import com.massana2110.pokeapp.core.domain.usecase.GetPokemonGeneration1UseCase
import com.massana2110.pokeapp.core.domain.usecase.SearchPokemonUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val pokemonListModule = module {
    factoryOf(::GetPokemonGeneration1UseCase)
    factoryOf(::SearchPokemonUseCase)
    viewModelOf(::PokemonListViewModel)
}