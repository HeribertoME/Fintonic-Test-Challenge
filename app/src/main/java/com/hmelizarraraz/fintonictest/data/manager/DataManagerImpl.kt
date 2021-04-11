package com.hmelizarraraz.fintonictest.data.manager

import com.hmelizarraraz.fintonictest.data.repositories.ApiRepository
import com.hmelizarraraz.fintonictest.data.repositories.DataBaseRepository
import javax.inject.Inject

/**
 * DataManagerImpl
 */
class DataManagerImpl @Inject constructor(
    private val apiRepository: ApiRepository,
    private val dataBaseRepository: DataBaseRepository
) : DataManager{

    /**
     * Method to get api repository
     */
    override fun getApiRepository(): ApiRepository = apiRepository

    /**
     * Method to get db repository
     */
    override fun getDBRepository(): DataBaseRepository = dataBaseRepository

}