package com.scribblex.pokemons.data.entities.detailpage

import com.google.gson.annotations.SerializedName


data class GenerationTwo(

    @SerializedName("crystal") var crystal: Crystal? = Crystal(),
    @SerializedName("gold") var gold: Gold? = Gold(),
    @SerializedName("silver") var silver: Silver? = Silver()

)