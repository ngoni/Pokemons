package com.scribblex.pokemons.data.entities.detailpage

import com.google.gson.annotations.SerializedName


data class GenerationSix(

    @SerializedName("omegaruby-alphasapphire") var omegarubyAlphasapphire: OmegarubyAlphasapphire? = OmegarubyAlphasapphire(),
    @SerializedName("x-y") var xy: XY? = XY()

)