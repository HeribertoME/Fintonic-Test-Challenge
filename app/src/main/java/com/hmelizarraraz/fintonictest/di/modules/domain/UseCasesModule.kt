package com.hmelizarraraz.fintonictest.di.modules.domain

import com.hmelizarraraz.fintonictest.data.manager.DataManager
import com.hmelizarraraz.fintonictest.domain.bussinesslogiccase.GetBeersUseCase
import com.hmelizarraraz.fintonictest.domain.bussinesslogiccase.impl.GetBeersUseCaseImpl
import dagger.Module
import dagger.Provides

/**
 * UseCasesModule
 */
@Module
class UseCasesModule {

    @Provides
    fun provideGetBeersUseCase(dataManager: DataManager): GetBeersUseCase = GetBeersUseCaseImpl(dataManager)
}