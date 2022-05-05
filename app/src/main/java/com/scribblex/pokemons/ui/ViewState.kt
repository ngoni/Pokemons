package com.scribblex.pokemons.ui

import com.scribblex.pokemons.data.entities.detailpage.DetailPayload
import com.scribblex.pokemons.data.entities.listingpage.Results

data class ListingScreenViewState(
    val state: State = State.Loading,
    val pokemonList: ArrayList<Results> = arrayListOf()
)


data class DetailScreenViewState(
    val state: State = State.Loading,
    val pokemonDetail: DetailPayload? = null
)

enum class State {
    Loading,
    Success,
    Error
}