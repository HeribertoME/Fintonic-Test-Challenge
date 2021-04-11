package com.hmelizarraraz.fintonictest.ui.app

import com.hmelizarraraz.fintonictest.data.models.AppError

/**
 * Configuration view
 */
interface IConfigView {

    /**
     * Method to show or hide progress
     *
     * @param showHideProgress flag to show or hide progress
     */
    fun showHideProgress(showHideProgress: Boolean)

    /**
     * Method to show alert error
     *
     * @param response response error
     */
    fun showAlertError(response: AppError)


}