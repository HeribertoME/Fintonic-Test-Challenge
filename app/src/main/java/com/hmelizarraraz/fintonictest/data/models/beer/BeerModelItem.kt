package com.hmelizarraraz.fintonictest.data.models.beer

/**
 * BeerModelItem
 */
data class BeerModelItem(
    val abv: Double = 0.0,
    val attenuation_level: Float = 0.0F,
    val boil_volume: BoilVolume? = null,
    val brewers_tips: String? = null,
    val contributed_by: String? = null,
    val description: String? = null,
    val ebc: Float = 0.0F,
    val first_brewed: String? = null,
    val food_pairing: List<String> = listOf(),
    val ibu: Float = 0.0F,
    val id: Int = 0,
    val image_url: String? = null,
    val ingredients: Ingredients? = null,
    val method: Method? = null,
    val name: String? = null,
    val ph: Double = 0.0,
    val srm: Float = 0.0F,
    val tagline: String? = null,
    val target_fg: Float = 0.0F,
    val target_og: Float = 0.0F,
    val volume: Volume? = null
)