package com.scribblex.pokemons.ui.pokemonlisting

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.scribblex.pokemons.MainDispatchRule
import com.scribblex.pokemons.TestDispatchers
import com.scribblex.pokemons.data.repository.FakePokemonRepository
import com.scribblex.pokemons.ui.ListingScreenViewState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class PokemonListViewModelTest {

    @get:Rule
    val mainDispatchRule = MainDispatchRule()
    private var testDispatcher = mainDispatchRule.dispatcherProvider as TestDispatchers

    private lateinit var viewModel: PokemonListViewModel
    private val repository = FakePokemonRepository()

    @Before
    fun setup() {
        viewModel = PokemonListViewModel(repository, testDispatcher)
    }

    @Test
    fun `verify initial viewState is State_Loading`() =
        runBlocking {
            viewModel.viewState.test {
                val loadingState = awaitItem()
                assertThat(loadingState).isEqualTo(ListingScreenViewState())
            }
        }
}