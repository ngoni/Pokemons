package com.scribblex.pokemons.data.repository

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.scribblex.pokemons.MainDispatchRule
import com.scribblex.pokemons.TestDispatchers
import com.scribblex.pokemons.data.entities.detailpage.DetailPayload
import com.scribblex.pokemons.data.entities.listingpage.PokemonResponse
import com.scribblex.pokemons.data.remote.PokemonRemoteDataSource
import com.scribblex.pokemons.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class PokemonRepositoryImplTest {

    @get:Rule
    val mainDispatchRule = MainDispatchRule()
    private var testDispatcher = mainDispatchRule.testDispatcher

    private lateinit var repository: PokemonRepositoryImpl
    private val dataSourceImpl = mock<PokemonRemoteDataSource>()
    private val pokemonId = 2

    @Before
    fun setup() {
        repository = PokemonRepositoryImpl(dataSourceImpl, testDispatcher)
    }

    @Test
    fun `GIVEN getPokemonDetail is called, THEN verify that request is made to RemoteDataSource`() =
        runTest {
            dataSourceImpl.getPokemonDetail(pokemonId)
            verify(dataSourceImpl, times(1)).getPokemonDetail(pokemonId)
        }

    @Test
    fun `GIVEN getAllPokemon is called, THEN verify that request is made to RemoteDataSource`() =
        runTest {
            dataSourceImpl.getAllPokemon()
            verify(dataSourceImpl, times(1)).getAllPokemon()
        }

    @Test
    fun `GIVEN getPokemonDetail is called, WHEN returned data is good THEN test should pass`() =
        runTest {
            whenever(dataSourceImpl.getPokemonDetail(pokemonId)).thenReturn(
                flow {
                    val detailPayload = DetailPayload()
                    emit(Resource.success(detailPayload))
                }
            )
            val payload = repository.getPokemonDetail(pokemonId).first()
            assertThat(DetailPayload()).isEqualTo(payload.data)
        }

}
