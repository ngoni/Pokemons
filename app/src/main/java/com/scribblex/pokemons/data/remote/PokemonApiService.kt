package com.scribblex.pokemons.data.remote


import com.scribblex.pokemons.data.entities.detailpage.DetailPayload
import com.scribblex.pokemons.data.entities.listingpage.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonApiService {
    @GET("/api/v2/pokemon")
    suspend fun getAllPokemon(): Response<PokemonResponse>

    @GET("api/v2/pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") id: String): Response<DetailPayload>
}