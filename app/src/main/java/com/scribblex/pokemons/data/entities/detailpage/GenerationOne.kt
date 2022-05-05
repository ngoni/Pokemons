package com.scribblex.pokemons.data.entities.detailpage

import com.google.gson.annotations.SerializedName


data class GenerationOne(

    @SerializedName("red-blue") var redBlue: RedBlue? = RedBlue(),
    @SerializedName("yellow")
    var yellow: Yellow? = Yellow()

)