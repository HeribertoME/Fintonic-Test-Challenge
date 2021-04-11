package com.hmelizarraraz.fintonictest.data.manager

import com.hmelizarraraz.fintonictest.data.repositories.ApiRepository
import com.hmelizarraraz.fintonictest.data.repositories.DataBaseRepository

/**
 * Class for data manager
 */
interface DataManager {

    /**
     * Method to get api repository
     */
    fun getApiRepository(): ApiRepository

    /**
     * Method to get db repository
     */
    fun getDBRepository(): DataBaseRepository

}