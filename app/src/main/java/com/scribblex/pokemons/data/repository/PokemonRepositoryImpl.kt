package com.scribblex.pokemons.data.repository


import com.scribblex.pokemons.DispatcherProvider
import com.scribblex.pokemons.data.entities.detailpage.DetailPayload
import com.scribblex.pokemons.data.entities.listingpage.Results
import com.scribblex.pokemons.data.remote.PokemonRemoteDataSource
import com.scribblex.pokemons.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val dataSourceImpl: PokemonRemoteDataSource,
    private val dispatcher: DispatcherProvider
) : PokemonRepository {

    override fun getAllPokemon(): Flow<Resource<ArrayList<Results>>> {
        return dataSourceImpl.getAllPokemon()
            .transform {
                val pokemonList: ArrayList<Results> = it.data?.results ?: arrayListOf()
                emit(Resource.success(pokemonList))
            }.flowOn(dispatcher.main)
    }

    override fun getPokemonDetail(id: Int): Flow<Resource<DetailPayload?>> {
        return dataSourceImpl.getPokemonDetail(id)
            .transform {
                emit(Resource.success(it.data))
            }.flowOn(dispatcher.main)
    }
}