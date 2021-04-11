package com.hmelizarraraz.fintonictest.data.models

data class Method(
    val fermentation: Fermentation,
    val mash_temp: List<MashTemp>,
    val twist: Any
)