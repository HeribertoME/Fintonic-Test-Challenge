package com.hmelizarraraz.fintonictest.data.repositories.impl

import com.hmelizarraraz.fintonictest.data.models.beer.BeerModelItem
import com.hmelizarraraz.fintonictest.data.repositories.ApiRepository
import com.hmelizarraraz.fintonictest.data.repositories.ConnectionApiService
import io.reactivex.Flowable
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val apiService: ConnectionApiService
): ApiRepository {

    override fun getBeers(): Flowable<ArrayList<BeerModelItem>> = apiService.getBeers(10)

}