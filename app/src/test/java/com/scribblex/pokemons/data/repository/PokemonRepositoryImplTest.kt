package com.scribblex.pokemons.data.repository

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.scribblex.pokemons.MainDispatchRule
import com.scribblex.pokemons.TestDispatchers
import com.scribblex.pokemons.data.entities.detailpage.DetailPayload
import com.scribblex.pokemons.data.entities.listingpage.PokemonResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class PokemonRepositoryImplTest {

    @get:Rule
    val mainDispatchRule = MainDispatchRule()

    private lateinit var repository: PokemonRepositoryImpl
    private val dataSourceImpl = FakePokemonRemoteDataSource()
    private val pokemonId = 2

    @Before
    fun setup() {
        val testDispatcher = mainDispatchRule.dispatcherProvider as TestDispatchers
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
    fun `GIVEN getPokemonDetail is called and successful, THEN returned response should matched expected data`() =
        runTest {
            val payload = repository.getPokemonDetail(pokemonId).first()
            assertThat(DetailPayload()).isEqualTo(payload.data)
        }

    @Test
    fun `GIVEN getAllPokemon is called and successful,THEN the returned response should match expected data`() =
        runTest {
            repository.getAllPokemon().test {
                val item = awaitItem()
                cancelAndConsumeRemainingEvents()
                assertThat(PokemonResponse().results).isEqualTo(item.data)
            }
        }
}
