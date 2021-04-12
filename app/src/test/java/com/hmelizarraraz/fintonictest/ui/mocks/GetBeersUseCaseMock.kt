package com.hmelizarraraz.fintonictest.ui.mocks

import com.hmelizarraraz.fintonictest.data.models.AppError
import com.hmelizarraraz.fintonictest.data.models.presentation.BeerUIModel
import com.hmelizarraraz.fintonictest.domain.bussinesslogiccase.GetBeersUseCase
import io.reactivex.disposables.CompositeDisposable

class GetBeersUseCaseMock : GetBeersUseCase {

    var isSuccess: Boolean = false
    var beerList = mutableListOf<BeerUIModel>()

    private var mSuccess: ((MutableList<BeerUIModel>) -> Unit)? = null
    private var mError: ((AppError) -> Unit)? = null
    private val disposable = CompositeDisposable()

    /**
     * Method for success response
     *
     * @param success success action
     */
    override fun onSuccess(success: (MutableList<BeerUIModel>) -> Unit): GetBeersUseCase {
        this.mSuccess = success
        return this
    }

    /**
     * Method for error response
     *
     * @param error error action
     */
    override fun onError(error: (AppError) -> Unit): GetBeersUseCase {
        this.mError = error
        return this
    }

    /**
     * Method to subscribe and get data
     */
    override fun subscribe() {
        if (isSuccess) {
            mSuccess!!.invoke(beerList)
        } else {
            mError!!.invoke(AppError())
        }
    }

    /**
     * Method to unsubscribe observer
     */
    override fun unSubscribe() {
        disposable.clear()
    }

}
