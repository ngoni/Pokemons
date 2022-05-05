package com.scribblex.pokemons.data.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.scribblex.pokemons.data.entities.listingpage.Results
import com.scribblex.pokemons.data.repository.PokemonRepositoryImpl
import com.scribblex.pokemons.ui.ListingScreenViewState
import com.scribblex.pokemons.ui.State.Loading
import com.scribblex.pokemons.ui.State.Success
import com.scribblex.pokemons.ui.pokemonlisting.PokemonListViewModel
import com.scribblex.pokemons.utils.Resource
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class PokemonListViewModelTest {

    @get:Rule
    var instantExecutorRule =
        InstantTaskExecutorRule() // allows execution of background work on main thread sequentially

    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private lateinit var viewModel: PokemonListViewModel
    private val repository = mock<PokemonRepositoryImpl>()
    private val results = mock<Resource<ArrayList<Results>>>()
    private val viewState = ListingScreenViewState(Success)

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = mockNetworkResponse()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `GIVEN getAllPokemon returns successful response, THEN viewState is updated`() =
        runTest {
            val loadingState = viewModel.viewState.asStateFlow().value
            assertThat(ListingScreenViewState(Loading)).isEqualTo(loadingState)
            viewModel.getAllPokemon()
            val successState = viewModel.viewState.asStateFlow().value
            assertThat(viewState).isEqualTo(successState)
        }

    private fun mockNetworkResponse(): PokemonListViewModel {
        val getAllPokemon = {
            repository.getAllPokemon()
        }
        whenever(getAllPokemon()).thenReturn(
            flow {
                emit(results)
            }
        )
        return PokemonListViewModel(repository)
    }
}