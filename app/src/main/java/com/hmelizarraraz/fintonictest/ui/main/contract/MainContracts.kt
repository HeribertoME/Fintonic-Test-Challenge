package com.hmelizarraraz.fintonictest.ui.main.contract

import com.hmelizarraraz.fintonictest.data.models.presentation.BeerUIModel
import com.hmelizarraraz.fintonictest.ui.commons.BaseContract

/**
 * Contracts for main view
 */
object MainContracts {

    interface IMainView : BaseContract.IBaseView {

        /**
         * Method to update data
         *
         * @param data beer ui model
         */
        fun updateData(data: MutableList<BeerUIModel>)

    }

    interface IMainPresenter : BaseContract.IBasePresenter<IMainView> {

        /**
         * Method to get beer list data
         */
        fun getBeerList()

        /**
         * Method to unsubscribe observer
         */
        fun unSubscribe()

    }

}