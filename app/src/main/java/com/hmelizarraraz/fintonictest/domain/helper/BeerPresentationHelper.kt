package com.hmelizarraraz.fintonictest.domain.helper

import com.hmelizarraraz.fintonictest.data.database.entities.BeerEntity
import com.hmelizarraraz.fintonictest.data.models.presentation.BeerUIModel

/**
 * BeerPresentationHelper class to present beer model
 */
class BeerPresentationHelper {

    /**
     * Method to get beer list
     *
     * @param beers beer entity list
     *
     * @return beer ui model list
     */
    fun getBeerList(beers: List<BeerEntity>): MutableList<BeerUIModel> {
        val list = arrayListOf<BeerUIModel>()
        for (beer in beers){
            val item = BeerUIModel(
                beer.id,
                beer.name,
                beer.isFavorite,
                beer.imageUrl
            )
            list.add(item)
        }
        return list
    }

}