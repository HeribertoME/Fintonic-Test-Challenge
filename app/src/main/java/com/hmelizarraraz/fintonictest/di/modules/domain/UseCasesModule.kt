package com.hmelizarraraz.fintonictest.di.modules.domain

import com.hmelizarraraz.fintonictest.data.manager.DataManager
import com.hmelizarraraz.fintonictest.domain.bussinesslogiccase.GetBeersUseCase
import com.hmelizarraraz.fintonictest.domain.bussinesslogiccase.GetDetailUseCase
import com.hmelizarraraz.fintonictest.domain.bussinesslogiccase.impl.GetBeersUseCaseImpl
import com.hmelizarraraz.fintonictest.domain.bussinesslogiccase.impl.GetDetailUseCaseImpl
import dagger.Module
import dagger.Provides

/**
 * UseCasesModule
 */
@Module
class UseCasesModule {

    /**
     * Method to provide get beers use case
     *
     * @param dataManager data manager instance
     *
     * @return get beer use case instance
     */
    @Provides
    fun provideGetBeersUseCase(dataManager: DataManager): GetBeersUseCase = GetBeersUseCaseImpl(dataManager)

    /**
     * Method to provide get detail use case instance
     *
     * @param dataManager data manager instance
     *
     * @return get detail use case instance
     */
    @Provides
    fun provideGetDetailUseCase(dataManager: DataManager): GetDetailUseCase = GetDetailUseCaseImpl(dataManager)

}