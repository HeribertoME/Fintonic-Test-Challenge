package com.hmelizarraraz.fintonictest.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.hmelizarraraz.fintonictest.R
import com.hmelizarraraz.fintonictest.data.models.presentation.BeerUIModel
import com.hmelizarraraz.fintonictest.databinding.ItemBeerBinding

/**
 * MainAdapter
 * Class to get adapter to main list
 */
class MainAdapter(
    private val items: MutableList<BeerUIModel>,
    private val listener: MainOnClickListener
): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    /**
     * Context instance
     */
    private lateinit var mContext: Context

    /**
     * onCreateViewHolder
     *
     * @param parent view group instance
     * @param viewType view type of recycler view
     *
     * @return view holder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_beer, parent, false)
        return MainViewHolder(view)
    }

    /**
     * getItemCount
     *
     * @return items size
     */
    override fun getItemCount(): Int = items.size

    /**
     * onBindViewHolder
     *
     * @param holder holder view of list item
     * @param position position of loop
     */
    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val beer = items[position]

        with(holder) {
            binding.root.setOnClickListener {
                listener.onItemClickListener(beer.id)
            }

            binding.tvName.text = beer.name ?: ""
            binding.cbFavorite.isChecked = beer.isFavorite

            val url = beer.imageUrl ?: ""

            if (url.isEmpty()) {
                binding.ivBeer.setImageResource(R.drawable.ic_error)
            } else {
                Glide
                    .with(mContext)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.ivBeer)
            }
        }
    }

    /**
     * MainViewHolder
     *
     * @param view view holder
     */
    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemBeerBinding.bind(view)
    }

    /**
     * MainOnClickListener
     * Click listener to response on items
     */
    interface MainOnClickListener {

        /**
         * Method to response on click item
         */
        fun onItemClickListener(id: Int)
    }
}