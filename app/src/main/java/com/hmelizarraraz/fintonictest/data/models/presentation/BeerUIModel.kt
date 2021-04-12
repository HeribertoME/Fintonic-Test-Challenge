package com.hmelizarraraz.fintonictest.data.models.presentation

/**
 * Beer UI Model
 *
 * Class to present data to ui
 *
 * @param id beer id value
 * @param name beer name value
 */
data class BeerUIModel(
    var id: Int = 0,
    var name: String? = null,
    var isFavorite: Boolean = false,
    var imageUrl: String? = null
)