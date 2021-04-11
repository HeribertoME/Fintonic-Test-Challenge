package com.hmelizarraraz.fintonictest.ui.commons

import com.hmelizarraraz.fintonictest.data.models.AppError

/**
 * Base
 */
object BaseContract {

    /**
     * BaseView
     */
    interface IBaseView {

        /**
         * Method to show progress
         */
        fun showProgress()

        /**
         * Method to hide progress
         */
        fun hideProgress()

        /**
         * Method for error response
         *
         * @param response response error
         */
        fun onError(response: AppError)

    }

    /**
     * BasePresenter
     */
    interface IBasePresenter<T : IBaseView> {

        /**
         * Method to set generic view
         *
         * @param view view
         */
        fun setView(view: T?)

        /**
         * Method on destroy
         */
        fun onDestroy()

    }
}