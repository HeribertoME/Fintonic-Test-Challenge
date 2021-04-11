package com.hmelizarraraz.fintonictest.ui.detail.presenter

import com.hmelizarraraz.fintonictest.domain.bussinesslogiccase.GetDetailUseCase
import com.hmelizarraraz.fintonictest.ui.commons.BasePresenter
import com.hmelizarraraz.fintonictest.ui.detail.contract.DetailContract
import javax.inject.Inject

/**
 * DetailPresenter class
 */
class DetailPresenter @Inject constructor(
    private val getDetailUseCase: GetDetailUseCase
) : BasePresenter<DetailContract.IDetailView>(), DetailContract.IDetailPresenter {

    /**
     * Method to get details
     *
     * @param id id value for beer entity
     */
    override fun requestDetails(id: Int) {
        getDetailUseCase
            .onSuccess {
                mView?.showDetails(it)
            }
            .onError {
                mView?.onError(it)
            }
            .subscribe(id)
    }

    /**
     * Method to unsubscribe observer
     */
    override fun unSubscribe() {
        getDetailUseCase.unSubscribe()
    }

}