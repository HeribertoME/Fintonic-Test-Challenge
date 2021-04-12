package com.hmelizarraraz.fintonictest.data.models.beer

data class Ingredients(
    val hops: List<Hop>,
    val malt: List<Malt>,
    val yeast: String
)