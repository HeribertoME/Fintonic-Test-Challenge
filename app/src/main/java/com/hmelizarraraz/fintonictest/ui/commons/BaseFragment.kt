package com.hmelizarraraz.fintonictest.ui.commons

import android.content.Context
import androidx.fragment.app.Fragment
import com.hmelizarraraz.fintonictest.data.models.AppError
import com.hmelizarraraz.fintonictest.di.component.Injectable
import com.hmelizarraraz.fintonictest.ui.app.IConfigView

/**
 * BaseFragment
 *
 * Implement Injectable
 */
open class BaseFragment : Fragment(), BaseContract.IBaseView, Injectable {

    /**
     * Config view interface
     */
    protected var mConfigView: IConfigView? = null

    /**
     * onAttach
     *
     * @param context context instance
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IConfigView) {
            mConfigView = context
        }
    }

    /**
     * Method to show progress
     */
    override fun showProgress() {
        mConfigView?.showHideProgress(true)
    }

    /**
     * Method to hide progress
     */
    override fun hideProgress() {
        mConfigView?.showHideProgress(false)
    }

    /**
     * Method for error response
     *
     * @param response response error
     */
    override fun onError(response: AppError) {
        mConfigView?.showAlertError(response)
    }

    /**
     * onDetach
     */
    override fun onDetach() {
        super.onDetach()
        mConfigView = null
    }
}