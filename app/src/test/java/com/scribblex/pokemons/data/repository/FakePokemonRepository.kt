package com.scribblex.pokemons.data.repository

import com.scribblex.pokemons.data.entities.detailpage.DetailPayload
import com.scribblex.pokemons.data.entities.listingpage.Results
import com.scribblex.pokemons.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePokemonRepository : PokemonRepository {
    override fun getAllPokemon(): Flow<Resource<ArrayList<Results>>> {
        return flow {
            val list = arrayListOf<Results>()
            emit(Resource.success(list))
        }
    }

    override fun getPokemonDetail(id: Int): Flow<Resource<DetailPayload?>> {
        return flow {
            val detailPayload = DetailPayload()
            emit(Resource.success(detailPayload))
        }
    }
}