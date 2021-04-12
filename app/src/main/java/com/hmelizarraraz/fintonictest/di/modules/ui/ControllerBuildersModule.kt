package com.hmelizarraraz.fintonictest.di.modules.ui

import com.hmelizarraraz.fintonictest.ui.main.controllers.MainController
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * ControllerBuildersModule
 */
@Module
abstract class ControllerBuildersModule {

    /**
     * Method to build main controller for inject
     *
     * @return MainController
     */
    @ContributesAndroidInjector
    abstract fun buildMainController(): MainController

}