package com.scribblex.pokemons.data.entities.detailpage

import com.google.gson.annotations.SerializedName


data class GenerationFour(

    @SerializedName("diamond-pearl") var diamondPearl: DiamondPearl? = DiamondPearl(),
    @SerializedName("heartgold-soulsilver") var heartgoldSoulsilver: HeartgoldSoulsilver? = HeartgoldSoulsilver(),
    @SerializedName("platinum") var platinum: Platinum? = Platinum()

)