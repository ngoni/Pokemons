package com.scribblex.pokemons.ui

import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onRoot
import androidx.navigation.compose.rememberNavController
import com.scribblex.pokemons.MainActivity
import com.scribblex.pokemons.data.entities.detailpage.DetailPayload
import com.scribblex.pokemons.data.entities.detailpage.Type
import com.scribblex.pokemons.data.entities.detailpage.Types
import com.scribblex.pokemons.ui.State.Success
import com.scribblex.pokemons.ui.pokemondetail.PokemonDetailScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class PokemonDetailScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun verify_view_layout_has_defined_elements() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            val navigationActions = remember(navController) {
                PokemonAppNavigationActions(navController = navController)
            }
            val data = testData()
            val viewState = MutableStateFlow(data)
            PokemonDetailScreen(
                _navigationActions = navigationActions,
                viewState = viewState
            )
        }
        composeTestRule
            .onRoot()
            .onChildren()[0].onChildren()[0].onChildren()[0].onChildren().assertCountEquals(5)

    }

    private fun testData(): DetailScreenViewState {
        return DetailScreenViewState(
            state = Success,
            pokemonDetail = DetailPayload(
                name = "pidgeot",
                height = 15,
                weight = 395,
                types = arrayListOf(
                    Types(type = Type(name = "normal")),
                    Types(type = Type(name = "flying")),
                )
            )
        )
    }
}