package com.hmelizarraraz.fintonictest.di.component

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

/**
 * ActivityLifecycleCallbacksImpl
 */
class ActivityLifecycleCallbacksImpl : Application.ActivityLifecycleCallbacks {

    /**
     * onActivityCreated
     * @param activity activity instance
     * @param savedInstanceState save instance
     */
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        handleActivity(activity)
    }

    /**
     * onActivityStarted
     * @param activity activity instance
     */
    override fun onActivityStarted(activity: Activity) {
        //do nothing
    }

    /**
     * onActivityResumed
     * @param activity activity instance
     */
    override fun onActivityResumed(activity: Activity) {
        //do nothing
    }

    /**
     * onActivityPaused
     * @param activity activity instance
     */
    override fun onActivityPaused(activity: Activity) {
        //do nothing
    }

    /**
     * onActivityStopped
     * @param activity activity instance
     */
    override fun onActivityStopped(activity: Activity) {
        //do nothing
    }

    /**
     * onActivityDestroyed
     * @param activity activity instance
     */
    override fun onActivityDestroyed(activity: Activity) {
        //do nothing
    }

    /**
     * onActivitySaveInstanceState
     * @param activity activity instance
     * @param outState bundle instance
     */
    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // do nothing
    }

    /**
     * Method to handle activity
     *
     * @param activity activity instance
     */
    private fun handleActivity(activity: Activity) {
        if (activity is HasSupportFragmentInjector) {
            AndroidInjection.inject(activity)
        }
        if (activity is FragmentActivity) {
            activity.supportFragmentManager
                .registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
                    /**
                     * onFragmentCreated
                     *
                     * @param fm fragment manager instance
                     * @param f fragment instance
                     * @param savedInstanceState bundle instance
                     */
                    override fun onFragmentCreated(fm: FragmentManager, f: Fragment, savedInstanceState: Bundle?) {
                        if (f is Injectable) {
                            AndroidSupportInjection.inject(f)
                        }
                    }
                }, true)
        }
    }

}