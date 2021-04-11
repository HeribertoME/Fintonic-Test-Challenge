package com.hmelizarraraz.fintonictest.di.modules.ui

import com.hmelizarraraz.fintonictest.ui.detail.fragments.DetailFragment
import com.hmelizarraraz.fintonictest.ui.main.fragments.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * FragmentBuildersModule
 */
@Module
abstract class FragmentBuildersModule {

    /**
     * Method to build main fragment for inject
     *
     * @return MainFragment
     */
    @ContributesAndroidInjector
    abstract fun buildMainFragment(): MainFragment

    /**
     * Method to build detail fragment for inject
     *
     * @return DetailFragment
     */
    @ContributesAndroidInjector
    abstract fun buildDetailFragment(): DetailFragment

}