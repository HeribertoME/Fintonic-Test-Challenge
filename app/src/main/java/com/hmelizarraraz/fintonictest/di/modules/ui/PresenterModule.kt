package com.hmelizarraraz.fintonictest.di.modules.ui

import com.hmelizarraraz.fintonictest.domain.bussinesslogiccase.GetBeersUseCase
import com.hmelizarraraz.fintonictest.domain.bussinesslogiccase.GetDetailUseCase
import com.hmelizarraraz.fintonictest.ui.detail.contract.DetailContract
import com.hmelizarraraz.fintonictest.ui.detail.presenter.DetailPresenter
import com.hmelizarraraz.fintonictest.ui.main.contract.MainContracts
import com.hmelizarraraz.fintonictest.ui.main.presenter.MainPresenter
import dagger.Module
import dagger.Provides

/**
 * PresenterModule
 */
@Module
class PresenterModule {

    /**
     * Method to provide main presenter
     *
     * @return MainContracts.IMainPresenter
     */
    @Provides
    fun provideMainPresenter(getBeersUseCase: GetBeersUseCase): MainContracts.IMainPresenter = MainPresenter(getBeersUseCase)

    /**
     * Method to provide detail presenter
     *
     * @return DetailContracts.IDetailPresenter
     */
    @Provides
    fun provideDetailPresenter(getDetailUseCase: GetDetailUseCase): DetailContract.IDetailPresenter = DetailPresenter(getDetailUseCase)

}