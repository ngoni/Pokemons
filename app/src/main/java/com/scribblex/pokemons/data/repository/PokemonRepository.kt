package com.scribblex.pokemons.data.repository

import com.scribblex.pokemons.data.entities.detailpage.DetailPayload
import com.scribblex.pokemons.data.entities.listingpage.Results
import com.scribblex.pokemons.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getAllPokemon(): Flow<Resource<ArrayList<Results>>>
    fun getPokemonDetail(id: String): Flow<Resource<DetailPayload?>>
}