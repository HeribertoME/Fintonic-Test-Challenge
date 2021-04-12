package com.hmelizarraraz.fintonictest.ui.detail.contract

import com.hmelizarraraz.fintonictest.data.database.entities.BeerEntity
import com.hmelizarraraz.fintonictest.ui.commons.BaseContract

/**
 * Contracts to detail
 */
object DetailContract {

    /**
     * Interface for detail view
     */
    interface IDetailView : BaseContract.IBaseView {

        /**
         * Method to set details
         */
        fun showDetails(beer: BeerEntity)

    }

    /**
     * Interface for detail presenter
     */
    interface IDetailPresenter: BaseContract.IBasePresenter<IDetailView> {

        /**
         * Method to get details
         *
         * @param id id value for beer entity
         */
        fun requestDetails(id: Int)

        /**
         * Method to unsubscribe observer
         */
        fun unSubscribe()

    }
}