package com.hmelizarraraz.fintonictest.ui.detail.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.hmelizarraraz.fintonictest.databinding.FragmentDetailBinding
import com.hmelizarraraz.fintonictest.ui.main.controllers.MainController

/**
 * DetailFragment
 * Class to show beer detail
 */
class DetailFragment : Fragment() {

    /**
     * View binding
     */
    private lateinit var mBinding: FragmentDetailBinding

    /**
     * onCreateView
     *
     * @param inflater layout inflater instance
     * @param container view group
     * @param savedInstanceState bundle instance
     *
     * @return fragment view
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentDetailBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.toolbar.title = "Some title"
    }

}