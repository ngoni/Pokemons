package com.scribblex.pokemons.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.scribblex.pokemons.ui.theme.PokemonsTheme

@Composable
fun PokemonApp() {
    PokemonsTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            PokemonAppNavigationActions(navController = navController)
        }
        AppNavGraph(
            navController = navController,
            navigationActions = navigationActions
        )
    }
}