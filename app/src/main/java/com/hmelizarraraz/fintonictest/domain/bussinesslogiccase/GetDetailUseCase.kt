package com.hmelizarraraz.fintonictest.domain.bussinesslogiccase

import com.hmelizarraraz.fintonictest.data.database.entities.BeerEntity
import com.hmelizarraraz.fintonictest.data.models.AppError

/**
 * GetDetailUseCase
 */
interface GetDetailUseCase {

    /**
     * Method for success response
     *
     * @param success success action
     */
    fun onSuccess(success: (BeerEntity) -> Unit): GetDetailUseCase

    /**
     * Method for error response
     *
     * @param error error action
     */
    fun onError(error: (AppError) -> Unit): GetDetailUseCase

    /**
     * Method to subscribe and get data
     */
    fun subscribe(id: Int)

    /**
     * Method to unsubscribe observer
     */
    fun unSubscribe()

}