package com.scribblex.pokemons.data.remote


import com.scribblex.pokemons.data.entities.detailpage.DetailPayload
import com.scribblex.pokemons.data.entities.listingpage.PokemonResponse
import com.scribblex.pokemons.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface PokemonRemoteDataSource {
    fun getAllPokemon(): Flow<Resource<PokemonResponse>>
    fun getPokemonDetail(id: Int): Flow<Resource<DetailPayload>>
}

class PokemonRemoteDataSource @Inject constructor(
    private val pokemonApiService: PokemonApiService
) : BaseDataSource() {
    fun getAllPokemon(): Flow<Resource<PokemonResponse>> = flow {
        val response = getResult { pokemonApiService.getAllPokemon() }
        emit(response)
    }

    fun getPokemonDetail(id: Int): Flow<Resource<DetailPayload>> = flow {
        val response = getResult { pokemonApiService.getPokemonDetails(id = id) }
        emit(response)
    }
}