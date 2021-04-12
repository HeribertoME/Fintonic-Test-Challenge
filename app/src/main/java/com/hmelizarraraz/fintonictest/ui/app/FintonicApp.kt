package com.hmelizarraraz.fintonictest.ui.app

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.multidex.BuildConfig
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.hmelizarraraz.fintonictest.di.component.AppInjector
import com.hmelizarraraz.fintonictest.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class FintonicApp : MultiDexApplication(), HasActivityInjector {

    /**
     * Activity injector instance
     */
    @Inject
    lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    /**
     * onCreate app
     */
    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
            .inject(this)
        AppInjector.init(this)

        // Used Timber for logs
        Timber.plant(Timber.DebugTree())

        instance = this
    }

    companion object {

        /**
         * Application object
         */
        var instance: FintonicApp? = null
    }

    /**
     * activityInjector
     *
     * @return AndroidInjector<Activity>
     */
    override fun activityInjector(): AndroidInjector<Activity> = mActivityInjector

}