package com.hmelizarraraz.fintonictest.di.component

import android.app.Application

/**
 * AppInjector
 */
class AppInjector {

    companion object {

        /**
         * Method to init configuration
         */
        fun init(app: Application) {
            app.registerActivityLifecycleCallbacks(ActivityLifecycleCallbacksImpl())
        }

    }

}