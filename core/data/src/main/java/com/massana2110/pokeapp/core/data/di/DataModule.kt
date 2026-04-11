package com.massana2110.pokeapp.core.data.di

import com.massana2110.pokeapp.core.data.repository.PokemonRepositoryImpl
import com.massana2110.pokeapp.core.domain.repository.PokemonRepository
import org.koin.dsl.module

val dataModule = module {
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
}
