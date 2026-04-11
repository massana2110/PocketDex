package com.massana2110.pokeapp

import android.app.Application
import com.massana2110.pokeapp.core.data.di.dataModule
import com.massana2110.pokeapp.core.network.di.networkModule
import com.massana2110.pokeapp.di.pokemonListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PocketDexApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PocketDexApplication)
            modules(
                networkModule,
                dataModule,
                pokemonListModule
            )
        }
    }
}
