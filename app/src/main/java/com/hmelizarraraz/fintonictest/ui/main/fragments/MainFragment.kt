package com.hmelizarraraz.fintonictest.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.hmelizarraraz.fintonictest.data.models.presentation.BeerUIModel
import com.hmelizarraraz.fintonictest.databinding.FragmentMainBinding
import com.hmelizarraraz.fintonictest.ui.main.adapter.MainAdapter

/**
 * Main fragment to show view
 */
class MainFragment : Fragment(), MainAdapter.MainOnClickListener {

    /**
     * View binding
     */
    private lateinit var mBinding: FragmentMainBinding

    /**
     * Beer list
     */
    private val mBeerList = mutableListOf(
        BeerUIModel(0, "Corona", imageUrl = "https://kaikucaffelatte.com/blog/wp-content/uploads/2020/03/shutterstock_510679489-scaled.jpg"), BeerUIModel(1, "Tecate", imageUrl = "https://images.punkapi.com/v2/227.png"),
        BeerUIModel(1, "Sol")
    )
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

    }

    /**
     * Method to response on click item
     */
    override fun onItemClickListener(item: BeerUIModel) {
        Toast.makeText(context, "Item = $item", Toast.LENGTH_LONG).show()
    }

    /**
     * onDestroyView
     */
    override fun onDestroyView() {
        super.onDestroyView()
    }

}