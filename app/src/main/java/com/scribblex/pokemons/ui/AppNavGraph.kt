package com.scribblex.pokemons.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.scribblex.pokemons.ui.PokemonAppDestinations.POKEMON_DETAIL_ROUTE
import com.scribblex.pokemons.ui.PokemonAppDestinations.POKEMON_LIST_ROUTE
import com.scribblex.pokemons.ui.pokemondetail.PokemonDetailScreen
import com.scribblex.pokemons.ui.pokemondetail.PokemonDetailViewModel
import com.scribblex.pokemons.ui.pokemonlisting.PokemonListScreen
import com.scribblex.pokemons.ui.pokemonlisting.PokemonListViewModel
import com.scribblex.pokemons.utils.Constants.POKEMON_ID
import com.scribblex.pokemons.utils.Constants.UN_DEFINED


@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = POKEMON_LIST_ROUTE,
    navigationActions: PokemonAppNavigationActions
) {
    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = POKEMON_LIST_ROUTE) {
            val viewModel = hiltViewModel<PokemonListViewModel>()
            val viewState = viewModel.viewState
            PokemonListScreen(navigationActions, viewState)
        }

        val args = listOf(
            navArgument(POKEMON_ID) {
                type = NavType.IntType
            }
        )

        val route = "$POKEMON_DETAIL_ROUTE/{id}"
        composable(route = route, arguments = args) { backStackEntry ->
            val pokemonId =
                backStackEntry.arguments?.getInt(POKEMON_ID) ?: UN_DEFINED
            val viewModel = hiltViewModel<PokemonDetailViewModel>()
            viewModel.getPokemonDetail(pokemonId)
            val viewState = viewModel.viewState
            PokemonDetailScreen(navigationActions, viewState)
        }
    }
}