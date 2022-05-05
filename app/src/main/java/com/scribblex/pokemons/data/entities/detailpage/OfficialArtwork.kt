package com.scribblex.pokemons.data.entities.detailpage

import com.google.gson.annotations.SerializedName


data class OfficialArtwork(

    @SerializedName("front_default") var frontDefault: String? = null

)