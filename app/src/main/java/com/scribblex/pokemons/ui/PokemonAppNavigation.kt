package com.scribblex.pokemons.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.scribblex.pokemons.ui.PokemonAppDestinations.POKEMON_DETAIL_ROUTE
import com.scribblex.pokemons.ui.PokemonAppDestinations.POKEMON_LIST_ROUTE

/**
 * Destinations used in the [PokemonApp].
 */
object PokemonAppDestinations {
    const val POKEMON_LIST_ROUTE = "home"
    const val POKEMON_DETAIL_ROUTE = "pokemon_detail"
}

/**
 * Models the navigation actions in the app.
 */
class PokemonAppNavigationActions(navController: NavHostController) {
    val navigateToPokemonList = {
        navController.navigate(POKEMON_LIST_ROUTE) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // re-selecting the same item
            launchSingleTop = true
            // Restore state when re-selecting a previously selected item
            restoreState = true
        }
    }

    val navigateToPokemonDetail = fun(id: Int) {
        val navigate = "$POKEMON_DETAIL_ROUTE/$id"
        navController.navigate(route = navigate) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}