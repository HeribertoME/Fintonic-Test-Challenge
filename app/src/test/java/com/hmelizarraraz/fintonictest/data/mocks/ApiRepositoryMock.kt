package com.hmelizarraraz.fintonictest.data.mocks

import com.hmelizarraraz.fintonictest.data.models.beer.BeerModelItem
import com.hmelizarraraz.fintonictest.data.repositories.ApiRepository
import io.reactivex.Flowable

class ApiRepositoryMock : ApiRepository {

    override fun getBeers(): Flowable<ArrayList<BeerModelItem>> {
        return Flowable.fromArray()
    }

}
