package com.scribblex.pokemons.ui.components

import androidx.compose.runtime.remember
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import com.scribblex.pokemons.ui.DetailScreenViewState
import com.scribblex.pokemons.ui.PokemonAppNavigationActions
import com.scribblex.pokemons.ui.State.Success
import org.junit.Rule
import org.junit.Test

class PokemonTopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun check_back_button_is_clickable_on_top_app_bar() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val navigationActions = remember(navController) {
                PokemonAppNavigationActions(navController = navController)
            }
            val viewState = DetailScreenViewState(Success)
            PokemonTopAppBar(
                viewState = viewState,
                navigationActions = navigationActions
            )
        }
        composeTestRule
            .onNodeWithContentDescription("Back")
            .assertIsDisplayed()
    }

    @Test
    fun check_no_name_is_available_is_shown_for_title() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val navigationActions = remember(navController) {
                PokemonAppNavigationActions(navController = navController)
            }
            val viewState = DetailScreenViewState(Success)
            PokemonTopAppBar(
                viewState = viewState,
                navigationActions = navigationActions
            )
        }
        composeTestRule
            .onNodeWithText("No name available")
            .assertExists()
    }
}