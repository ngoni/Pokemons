package com.scribblex.pokemons.data.entities.detailpage

import com.google.gson.annotations.SerializedName


data class Versions(

    @SerializedName("generationOne") var generationOne: GenerationOne? = GenerationOne(),
    @SerializedName("generationTwo") var generationTwo: GenerationTwo? = GenerationTwo(),
    @SerializedName("generationThree") var generationThree: GenerationThree? = GenerationThree(),
    @SerializedName("generationFour") var generationFour: GenerationFour? = GenerationFour(),
    @SerializedName("generationFive") var generationFive: GenerationFive? = GenerationFive(),
    @SerializedName("generationSix") var generationSix: GenerationSix? = GenerationSix(),
    @SerializedName("generationSeven") var generationSeven: GenerationSeven? = GenerationSeven(),
    @SerializedName("generationEight") var generationEight: GenerationEight? = GenerationEight()

)