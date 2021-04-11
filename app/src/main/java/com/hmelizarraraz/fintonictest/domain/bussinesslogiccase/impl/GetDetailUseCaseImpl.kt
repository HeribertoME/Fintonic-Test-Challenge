package com.hmelizarraraz.fintonictest.domain.bussinesslogiccase.impl

import com.hmelizarraraz.fintonictest.data.database.entities.BeerEntity
import com.hmelizarraraz.fintonictest.data.manager.DataManager
import com.hmelizarraraz.fintonictest.data.models.AppError
import com.hmelizarraraz.fintonictest.domain.bussinesslogiccase.GetDetailUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Implementation for get detail use case
 */
class GetDetailUseCaseImpl @Inject constructor(
    private val dataManager: DataManager
) : GetDetailUseCase{

    private var mSuccess: ((BeerEntity) -> Unit)? = null
    private var mError: ((AppError) -> Unit)? = null

    private val disposable = CompositeDisposable()

    /**
     * Method for success response
     *
     * @param success success action
     */
    override fun onSuccess(success: (BeerEntity) -> Unit): GetDetailUseCase {
        this.mSuccess = success
        return this
    }

    /**
     * Method for error response
     *
     * @param error error action
     */
    override fun onError(error: (AppError) -> Unit): GetDetailUseCase {
        this.mError = error
        return this
    }

    /**
     * Method to subscribe and get data
     */
    override fun subscribe(id: Int) {
        disposable.add(
            dataManager.getDBRepository().beerDao().loadFlightEventById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    mSuccess!!.invoke(it)
                }, {e ->
                    mError!!.invoke(AppError())
                })
        )
    }

    /**
     * Method to unsubscribe observer
     */
    override fun unSubscribe() {
        disposable.clear()
    }
}