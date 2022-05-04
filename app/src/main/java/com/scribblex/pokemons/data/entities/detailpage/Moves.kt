package com.scribblex.pokemons.data.entities.detailpage

import com.google.gson.annotations.SerializedName


data class Moves(

    @SerializedName("move") var move: Move? = Move(),
    @SerializedName("version_group_details") var versionGroupDetails: ArrayList<VersionGroupDetails> = arrayListOf()

)