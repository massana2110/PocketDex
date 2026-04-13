package com.massana2110.pokeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.massana2110.pokeapp.PokemonDetailScreen
import com.massana2110.pokeapp.PokemonListScreen
import kotlinx.serialization.Serializable

@Serializable
data object PokemonList

@Serializable
data class PokemonDetail(val pokemonId: Int)

@Composable
fun PocketDexNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PokemonList
    ) {
        composable<PokemonList> {
            PokemonListScreen(
                onNavigateToPokemonDetail = { pokemonId ->
                    navController.navigate(PokemonDetail(pokemonId = pokemonId))
                }
            )
        }
        composable<PokemonDetail> { backStackEntry ->
            val args = backStackEntry.toRoute<PokemonDetail>()
            PokemonDetailScreen(
                pokemonId = args.pokemonId,
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}
