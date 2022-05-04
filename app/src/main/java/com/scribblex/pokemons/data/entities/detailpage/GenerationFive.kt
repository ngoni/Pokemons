package com.scribblex.pokemons.data.entities.detailpage

import com.google.gson.annotations.SerializedName


data class GenerationFive(

    @SerializedName("black-white") var blackWhite: BlackWhite? = BlackWhite()

)