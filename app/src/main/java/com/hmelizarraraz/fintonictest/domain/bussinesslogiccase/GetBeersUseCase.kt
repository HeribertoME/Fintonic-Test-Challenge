package com.hmelizarraraz.fintonictest.domain.bussinesslogiccase

import com.hmelizarraraz.fintonictest.data.models.AppError
import com.hmelizarraraz.fintonictest.data.models.presentation.BeerUIModel

/**
 * GetBeersUseCase
 */
interface GetBeersUseCase {

    /**
     * Method for success response
     *
     * @param success success action
     */
    fun onSuccess(success: (MutableList<BeerUIModel>) -> Unit): GetBeersUseCase

    /**
     * Method for error response
     *
     * @param error error action
     */
    fun onError(error: (AppError) -> Unit): GetBeersUseCase

    /**
     * Method to subscribe and get data
     */
    fun subscribe()

    /**
     * Method to unsubscribe observer
     */
    fun unSubscribe()

}
