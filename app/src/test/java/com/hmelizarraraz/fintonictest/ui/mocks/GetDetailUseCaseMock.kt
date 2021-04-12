package com.hmelizarraraz.fintonictest.ui.mocks

import com.hmelizarraraz.fintonictest.data.database.entities.BeerEntity
import com.hmelizarraraz.fintonictest.data.models.AppError
import com.hmelizarraraz.fintonictest.domain.bussinesslogiccase.GetDetailUseCase
import io.reactivex.disposables.CompositeDisposable

class GetDetailUseCaseMock : GetDetailUseCase {

    private var mSuccess: ((BeerEntity) -> Unit)? = null
    private var mError: ((AppError) -> Unit)? = null

    private val disposable = CompositeDisposable()

    var isSuccess: Boolean = false
    var beerEntity: BeerEntity? = null

    override fun onSuccess(success: (BeerEntity) -> Unit): GetDetailUseCase {
        this.mSuccess = success
        return this
    }

    override fun onError(error: (AppError) -> Unit): GetDetailUseCase {
        this.mError = error
        return this
    }

    override fun subscribe(id: Int) {
        if (isSuccess) {
            mSuccess!!.invoke(beerEntity ?: BeerEntity(0, "", false, "", ""))
        } else {
            mError!!.invoke(AppError())
        }
    }

    override fun unSubscribe() {
        disposable.clear()
    }

}
