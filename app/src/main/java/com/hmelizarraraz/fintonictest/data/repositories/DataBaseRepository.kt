package com.hmelizarraraz.fintonictest.data.repositories

import com.hmelizarraraz.fintonictest.data.database.daos.BeerDao

/**
 * DataBaseRepository
 */
interface DataBaseRepository {

    /**
     * Method to get beer dao instance
     *
     * @return beer dao instance
     */
    fun beerDao(): BeerDao

}