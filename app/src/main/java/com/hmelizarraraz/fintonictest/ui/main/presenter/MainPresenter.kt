package com.hmelizarraraz.fintonictest.ui.main.presenter

import com.hmelizarraraz.fintonictest.domain.bussinesslogiccase.GetBeersUseCase
import com.hmelizarraraz.fintonictest.ui.commons.BasePresenter
import com.hmelizarraraz.fintonictest.ui.main.contract.MainContracts
import javax.inject.Inject

/**
 * MainPresenter
 */
class MainPresenter @Inject constructor(
    private val getBeersUseCase: GetBeersUseCase
): BasePresenter<MainContracts.IMainView>(), MainContracts.IMainPresenter {

    /**
     * Method to get beer list data
     */
    override fun getBeerList() {
        mView?.showProgress()
        getBeersUseCase
            .onSuccess {
                mView?.hideProgress()
                mView?.updateData(it)
            }
            .onError {
                mView?.hideProgress()
                mView?.onError(it)
            }
            .subscribe()
    }

    /**
     * Method to unsubscribe observer
     */
    override fun unSubscribe() {
        getBeersUseCase.unSubscribe()
    }

}
