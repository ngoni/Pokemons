package com.scribblex.pokemons.data.entities.detailpage

import com.google.gson.annotations.SerializedName


data class GameIndices(

    @SerializedName("game_index") var gameIndex: Int? = null,
    @SerializedName("version") var version: Version? = Version()

)