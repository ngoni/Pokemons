package com.scribblex.pokemons.ui.pokemondetail

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.scribblex.pokemons.MainDispatchRule
import com.scribblex.pokemons.TestDispatchers
import com.scribblex.pokemons.data.entities.detailpage.DetailPayload
import com.scribblex.pokemons.data.entities.listingpage.PokemonResponse
import com.scribblex.pokemons.data.repository.FakePokemonRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PokemonDetailViewModelTest {

    @get:Rule
    val mainDispatchRule = MainDispatchRule()

    private lateinit var viewModel: PokemonDetailViewModel
    private val repository = FakePokemonRepository()
    private val pokemonId = 1

    @Before
    fun setup() {
        val testDispatcher = mainDispatchRule.dispatcherProvider as TestDispatchers
        viewModel = PokemonDetailViewModel(
            repository = repository,
            dispatcher = testDispatcher
        )
    }

    @Test
    fun `when getPokemonDetail call is successful, then verify viewState has pokemon details`() =
        runTest {
            viewModel.getPokemonDetail(id = pokemonId)
            val viewState = viewModel.viewState.value
            assertThat(viewState.pokemonDetail?.name).isEqualTo(DetailPayload().name)
        }

    @Test
    fun `when getAllPokemon call is successful, then the response should match expected data`() =
        runTest {
            repository.getAllPokemon().test {
                val item = awaitItem()
                cancelAndConsumeRemainingEvents()
                assertThat(PokemonResponse().results).isEqualTo(item.data)
            }
        }
}