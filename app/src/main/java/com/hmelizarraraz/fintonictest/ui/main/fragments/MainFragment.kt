package com.hmelizarraraz.fintonictest.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hmelizarraraz.fintonictest.data.models.presentation.BeerUIModel
import com.hmelizarraraz.fintonictest.databinding.FragmentMainBinding
import com.hmelizarraraz.fintonictest.ui.commons.BaseFragment
import com.hmelizarraraz.fintonictest.ui.main.adapter.MainAdapter
import com.hmelizarraraz.fintonictest.ui.main.contract.MainContracts
import timber.log.Timber
import java.util.*
import javax.inject.Inject

/**
 * Main fragment to show view for beer list
 */
class MainFragment : BaseFragment(), MainAdapter.MainOnClickListener, MainContracts.IMainView {

    /**
     * Presenter instance
     */
    @Inject
    lateinit var mPresenter: MainContracts.IMainPresenter
    /**
     * View binding
     */
    private lateinit var mBinding: FragmentMainBinding
    /**
     * Beer list
     */
    private val mBeerList = mutableListOf<BeerUIModel>()

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
        mBinding = FragmentMainBinding.inflate(inflater, container, false)
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
        val mainAdapter = MainAdapter(mBeerList, this)
        val gridLayoutManager = GridLayoutManager(context, 2)

        mBinding.rvBeers.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            adapter = mainAdapter
        }
        mPresenter.setView(this)
        mPresenter.getBeerList()
        mBinding.calendar
            .init()

        mBinding.calendar.setDaySelectedListener {
            Toast.makeText(requireContext(), "Day = $it", Toast.LENGTH_SHORT).show()
            val dates = mBinding.calendar.getSelectedDates()
            Timber.d(dates.toString())
        }
    }

    /**
     * Method to update data
     *
     * @param data beer ui model
     */
    override fun updateData(data: MutableList<BeerUIModel>) {
        mBeerList.clear()
        mBeerList.addAll(data)
        mBinding.rvBeers.adapter?.notifyDataSetChanged()
    }

    /**
     * Method to response on click item
     */
    override fun onItemClickListener(id: Int) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(id))
    }

    /**
     * onPause
     */
    override fun onPause() {
        super.onPause()
        mPresenter.unSubscribe()
    }

}