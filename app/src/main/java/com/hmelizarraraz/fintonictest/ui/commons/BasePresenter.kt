package com.hmelizarraraz.fintonictest.ui.commons

abstract class BasePresenter<T: BaseContract.IBaseView>: BaseContract.IBasePresenter<T> {

    /**
     * Base view
     */
    protected var mView: T? = null

    /**
     * Method to set generic view
     *
     * @param view view
     */
    override fun setView(view: T?) {
        if(mView == null){
            mView = view
        }
    }

    /**
     * Method on destroy
     */
    override fun onDestroy() {
        if(mView != null){
            mView!!.hideProgress()
            mView = null
        }
    }

}