package com.scribblex.pokemons.data.remote

import com.scribblex.pokemons.data.entities.detailpage.DetailPayload
import com.scribblex.pokemons.data.entities.listingpage.PokemonResponse
import com.scribblex.pokemons.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRemoteDataSource {
    fun getAllPokemon(): Flow<Resource<PokemonResponse>>
    fun getPokemonDetail(id: Int): Flow<Resource<DetailPayload>>
}