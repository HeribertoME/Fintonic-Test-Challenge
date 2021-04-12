package com.hmelizarraraz.fintonictest.ui.main.controllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hmelizarraraz.fintonictest.data.models.AppError
import com.hmelizarraraz.fintonictest.databinding.ActivityMainBinding
import com.hmelizarraraz.fintonictest.ui.app.IConfigView
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * MainController
 *
 * Main controller to show recyclerview with beer list
 */
class MainController : AppCompatActivity(), HasSupportFragmentInjector, IConfigView {

    /**
     * Dispatching android injector fragment
     */
    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var mBinding: ActivityMainBinding

    /**
     * supportFragmentInjector
     * allows inject fragments
     *
     * @return dispatching android injector
     */
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    /**
     * onCreate method
     *
     * @param savedInstanceState saved instance state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    /**
     * Method to show / hide progress bar
     */
    override fun showHideProgress(showHideProgress: Boolean) {
        if (showHideProgress) {
            mBinding.progressBar.visibility = View.VISIBLE
        } else {
            mBinding.progressBar.visibility = View.GONE
        }
    }

    override fun showAlertError(response: AppError) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
    }

}