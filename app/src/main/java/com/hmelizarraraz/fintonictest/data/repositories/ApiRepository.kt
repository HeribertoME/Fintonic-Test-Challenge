package com.hmelizarraraz.fintonictest.data.repositories

import com.hmelizarraraz.fintonictest.data.models.BeerModelItem
import io.reactivex.Flowable

/**
 * ApiRepository
 * Api invoke through this class. it use Retrofit and OKHttp as client.
*/
interface ApiRepository {

    fun getBeers(): Flowable<ArrayList<BeerModelItem>>
}