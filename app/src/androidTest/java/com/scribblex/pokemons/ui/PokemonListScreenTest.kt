package com.scribblex.pokemons.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.scribblex.pokemons.MainActivity
import com.scribblex.pokemons.data.entities.listingpage.Results
import com.scribblex.pokemons.ui.State.Success
import com.scribblex.pokemons.ui.pokemonlisting.PokemonListScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class PokemonListScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun check_if_the_listing_rows_has_three_items() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val navigationActions = remember(navController) {
                PokemonAppNavigationActions(navController = navController)
            }
            val data = testData()
            val viewState = MutableStateFlow(data)
            PokemonListScreen(_navigationActions = navigationActions, viewState = viewState)
        }
        composeTestRule.onRoot().onChildren()[0].onChildren()
            .assertCountEquals(3)
    }

    @Test
    fun check_if_listing_item_is_clickable() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val navigationActions = remember(navController) {
                PokemonAppNavigationActions(navController = navController)
            }
            val data = testData()
            val viewState = MutableStateFlow(data)
            PokemonListScreen(_navigationActions = navigationActions, viewState = viewState)
        }
        composeTestRule.onRoot()
            .onChildren()[0].onChildren()[0].assertHasClickAction()
    }

    @Composable
    private fun testData(): ListingScreenViewState {
        return ListingScreenViewState(
            state = Success,
            pokemonList = arrayListOf(
                Results(
                    name = "bulbasaur",
                    url = "https://pokeapi.co/api/v2/pokemon/1/"
                ),
                Results(
                    name = "ivysaur",
                    url = "https://pokeapi.co/api/v2/pokemon/2/"
                ),
                Results(
                    name = "venusaur",
                    url = "https://pokeapi.co/api/v2/pokemon/3/"
                ),
            )
        )
    }
}