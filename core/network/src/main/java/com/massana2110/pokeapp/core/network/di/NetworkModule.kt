package com.massana2110.pokeapp.core.network.di

import com.massana2110.pokeapp.core.network.RetrofitClient
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitClient.create() }
}
