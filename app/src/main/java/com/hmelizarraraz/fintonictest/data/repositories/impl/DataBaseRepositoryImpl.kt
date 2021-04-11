package com.hmelizarraraz.fintonictest.data.repositories.impl

import com.hmelizarraraz.fintonictest.data.database.daos.BeerDao
import com.hmelizarraraz.fintonictest.data.repositories.DataBaseRepository
import javax.inject.Inject

/**
 * DataBaseRepositoryImpl
 */
class DataBaseRepositoryImpl @Inject constructor(
    private val beerDao: BeerDao
) : DataBaseRepository {

    /**
     * Method to get beer dao instance
     *
     * @return beer dao instance
     */
    override fun beerDao(): BeerDao = beerDao

}