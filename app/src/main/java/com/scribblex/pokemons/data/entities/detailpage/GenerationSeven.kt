package com.scribblex.pokemons.data.entities.detailpage

import com.google.gson.annotations.SerializedName


data class GenerationSeven(

    @SerializedName("icons") var icons: Icons? = Icons(),
    @SerializedName("ultra-sun-ultra-moon") var ultraSunUltraMoon: UltraSunUltraMoon? = UltraSunUltraMoon()

)