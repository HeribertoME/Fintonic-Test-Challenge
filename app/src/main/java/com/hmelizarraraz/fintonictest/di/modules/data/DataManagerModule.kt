package com.hmelizarraraz.fintonictest.di.modules.data

import com.hmelizarraraz.fintonictest.data.manager.DataManager
import com.hmelizarraraz.fintonictest.data.manager.DataManagerImpl
import com.hmelizarraraz.fintonictest.data.repositories.ApiRepository
import com.hmelizarraraz.fintonictest.data.repositories.DataBaseRepository
import dagger.Module
import dagger.Provides

/**
 * DataManagerModule
 */
@Module
class DataManagerModule {

    @Provides
    fun providesDataManager(apiRepository: ApiRepository, dataBaseRepository: DataBaseRepository): DataManager =
        DataManagerImpl(apiRepository, dataBaseRepository)

}