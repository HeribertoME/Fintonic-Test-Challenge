package com.hmelizarraraz.fintonictest.data.repositories

import com.hmelizarraraz.fintonictest.data.models.beer.BeerModelItem
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ConnectionApiService {

    @GET("beers")
    fun getBeers(@Query("page") currentPage: Int): Flowable<ArrayList<BeerModelItem>>

}