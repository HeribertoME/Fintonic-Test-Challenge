package com.hmelizarraraz.fintonictest.di.component

import android.app.Application
import com.hmelizarraraz.fintonictest.di.modules.data.*
import com.hmelizarraraz.fintonictest.di.modules.domain.UseCasesModule
import com.hmelizarraraz.fintonictest.di.modules.ui.AppModule
import com.hmelizarraraz.fintonictest.di.modules.ui.ControllerBuildersModule
import com.hmelizarraraz.fintonictest.di.modules.ui.FragmentBuildersModule
import com.hmelizarraraz.fintonictest.di.modules.ui.PresenterModule
import com.hmelizarraraz.fintonictest.ui.app.FintonicApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * ApplicationComponent
 */
@Singleton
@Component(
    modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ControllerBuildersModule::class,
    FragmentBuildersModule::class,
    PresenterModule::class,
    UseCasesModule::class,
    DataManagerModule::class,
    RepositoryModule::class,
    ApiServiceModule::class,
    NetworkModule::class,
    DatabaseModule::class]
)
interface ApplicationComponent {

    /**
     * Method to inject application
     *
     * @param app app instance
     */
    fun inject(app: FintonicApp)

    /**
     * Builder interface
     */
    @Component.Builder
    interface Builder {

        /**
         * application
         *
         * @return builder
         */
        @BindsInstance
        fun application(app: Application): Builder

        /**
         * build
         *
         * @return ApplicationComponent
         */
        fun build(): ApplicationComponent

    }
}