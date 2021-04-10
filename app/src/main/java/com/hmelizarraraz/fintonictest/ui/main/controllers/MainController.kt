package com.hmelizarraraz.fintonictest.ui.main.controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hmelizarraraz.fintonictest.R

/**
 * MainController
 *
 * Main controller to show recyclerview with beer list
 */
class MainController : AppCompatActivity() {

    /**
     * onCreate method
     *
     * @param savedInstanceState saved instance state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}