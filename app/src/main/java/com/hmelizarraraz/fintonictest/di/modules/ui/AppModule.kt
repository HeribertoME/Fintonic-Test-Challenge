package com.hmelizarraraz.fintonictest.di.modules.ui

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * AppModule
 */
@Module
class AppModule {

    /**
     * Method to provide app context
     *
     * @param app application instance
     *
     * @return context
     */
    @Provides
    fun provideContext(app: Application): Context = app

}