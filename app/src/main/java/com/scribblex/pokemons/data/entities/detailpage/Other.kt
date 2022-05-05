package com.scribblex.pokemons.data.entities.detailpage

import com.google.gson.annotations.SerializedName


data class Other(

    @SerializedName("dream_world") var dreamWorld: DreamWorld? = DreamWorld(),
    @SerializedName("home") var home: Home? = Home(),
    @SerializedName("official-artwork") var officialArtwork: OfficialArtwork? = OfficialArtwork()

)