package com.scribblex.pokemons.data.repository

import com.scribblex.pokemons.data.entities.detailpage.DetailPayload
import com.scribblex.pokemons.data.entities.listingpage.PokemonResponse
import com.scribblex.pokemons.data.remote.PokemonRemoteDataSource
import com.scribblex.pokemons.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePokemonRemoteDataSource : PokemonRemoteDataSource {
    override fun getAllPokemon(): Flow<Resource<PokemonResponse>> {
        return flow {
            val list = PokemonResponse()
            emit(Resource.success(list))
        }
    }

    override fun getPokemonDetail(id: Int): Flow<Resource<DetailPayload>> {
        return flow {
            val list = DetailPayload()
            emit(Resource.success(list))
        }
    }
}