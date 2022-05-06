package com.scribblex.pokemons.ui.pokemondetail

import com.google.common.truth.Truth.assertThat
import com.scribblex.pokemons.MainDispatchRule
import com.scribblex.pokemons.data.entities.detailpage.DetailPayload
import com.scribblex.pokemons.data.repository.PokemonRepositoryImpl
import com.scribblex.pokemons.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class PokemonDetailViewModelTest {

    @get:Rule
    val mainDispatchRule = MainDispatchRule()

    private lateinit var viewModel: PokemonDetailViewModel
    private val repository = mock<PokemonRepositoryImpl>()
    private val pokemonDetail = mock<DetailPayload>()
    private val pokemonId = 1

    @Before
    fun setup() {
        viewModel = PokemonDetailViewModel(repository = repository)
    }

    @Test
    fun `when getPokemonDetail call is successful, then verify viewState has pokemon details`() = runTest {
        mockNetworkResponse()
        viewModel.getPokemonDetail(id = pokemonId)
        val viewState = viewModel.viewState.value
        assertThat(viewState.pokemonDetail?.name).isEqualTo(pokemonDetail.name)
    }

    private fun mockNetworkResponse() {
        whenever(repository.getPokemonDetail(pokemonId)).thenReturn(
            flow {
                Resource.success(pokemonDetail)
            }
        )
    }
}