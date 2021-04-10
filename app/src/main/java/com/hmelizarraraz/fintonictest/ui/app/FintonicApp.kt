package com.hmelizarraraz.fintonictest.ui.app

import android.app.Application

class FintonicApp : Application() {

    companion object {
        var instance: FintonicApp? = null
    }

    /**
     * onCreate app
     */
    override fun onCreate() {
        super.onCreate()

        instance = this
    }

}