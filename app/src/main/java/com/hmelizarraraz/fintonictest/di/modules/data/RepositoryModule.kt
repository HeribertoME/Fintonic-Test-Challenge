package com.hmelizarraraz.fintonictest.di.modules.data

import com.hmelizarraraz.fintonictest.data.database.daos.BeerDao
import com.hmelizarraraz.fintonictest.data.repositories.ApiRepository
import com.hmelizarraraz.fintonictest.data.repositories.ConnectionApiService
import com.hmelizarraraz.fintonictest.data.repositories.DataBaseRepository
import com.hmelizarraraz.fintonictest.data.repositories.impl.ApiRepositoryImpl
import com.hmelizarraraz.fintonictest.data.repositories.impl.DataBaseRepositoryImpl
import dagger.Module
import dagger.Provides

/**
 * RepositoryModule
 */
@Module
class RepositoryModule {

    @Provides
    fun provideApiRepository(apiService: ConnectionApiService): ApiRepository = ApiRepositoryImpl(apiService)

    @Provides
    fun providesDataBaseRepository(beerDao: BeerDao): DataBaseRepository = DataBaseRepositoryImpl(beerDao)

}