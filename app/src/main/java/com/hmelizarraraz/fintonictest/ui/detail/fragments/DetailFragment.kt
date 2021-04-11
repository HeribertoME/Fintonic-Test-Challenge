package com.hmelizarraraz.fintonictest.ui.detail.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hmelizarraraz.fintonictest.data.database.entities.BeerEntity
import com.hmelizarraraz.fintonictest.databinding.FragmentDetailBinding
import com.hmelizarraraz.fintonictest.ui.commons.BaseFragment
import com.hmelizarraraz.fintonictest.ui.detail.contract.DetailContract
import javax.inject.Inject

/**
 * DetailFragment
 * Class to show beer detail
 */
class DetailFragment : BaseFragment(), DetailContract.IDetailView {

    /**
     * View binding
     */
    private lateinit var mBinding: FragmentDetailBinding
    /**
     * Presenter instance
     */
    @Inject
    lateinit var mPresenter: DetailContract.IDetailPresenter
    /**
     * Safe arguments instance
     */
    private val mArgs: DetailFragmentArgs by navArgs()

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

    /**
     * onViewCreated
     *
     * @param view view created
     * @param savedInstanceState bundle instance
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.setView(this)
        mPresenter.requestDetails(mArgs.beer)
    }

    /**
     * Method to set details
     */
    override fun showDetails(beer: BeerEntity) {
        mBinding.toolbar.title = beer.name
        mBinding.tvDescriptionBeer.text = beer.description
        val url = beer.imageUrl
        if (url.isNotEmpty()) {
            Glide
                .with(requireActivity())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mBinding.ivBeerDetail)
        }
    }

    /**
     * onPause
     */
    override fun onPause() {
        super.onPause()
        mPresenter.unSubscribe()
    }

}