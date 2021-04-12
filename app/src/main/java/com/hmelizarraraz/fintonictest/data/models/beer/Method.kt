package com.hmelizarraraz.fintonictest.data.models.beer

data class Method(
    val fermentation: Fermentation,
    val mash_temp: List<MashTemp>,
    val twist: Any
)