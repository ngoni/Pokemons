package com.scribblex.pokemons.data.remote


import com.scribblex.pokemons.DispatcherProvider
import com.scribblex.pokemons.data.entities.detailpage.DetailPayload
import com.scribblex.pokemons.data.entities.listingpage.PokemonResponse
import com.scribblex.pokemons.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(
    private val pokemonApiService: PokemonApiService,
    private val dispatcher: DispatcherProvider
) : BaseDataSource(), PokemonRemoteDataSource {
    override fun getAllPokemon(): Flow<Resource<PokemonResponse>> = flow {
        val response = getResult { pokemonApiService.getAllPokemon() }
        emit(response)
    }.flowOn(dispatcher.main)

    override fun getPokemonDetail(id: Int): Flow<Resource<DetailPayload>> = flow {
        val response = getResult { pokemonApiService.getPokemonDetails(id = id) }
        emit(response)
    }.flowOn(dispatcher.main)
}