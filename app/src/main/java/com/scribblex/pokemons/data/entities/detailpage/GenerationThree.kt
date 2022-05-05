package com.scribblex.pokemons.data.entities.detailpage

import com.google.gson.annotations.SerializedName


data class GenerationThree(

    @SerializedName("emerald") var emerald: Emerald? = Emerald(),
    @SerializedName("firered-leafgreen") var fireredLeafgreen: FireredLeafgreen? = FireredLeafgreen(),
    @SerializedName("ruby-sapphire")
    var rubySapphire: RubySapphire? = RubySapphire()

)