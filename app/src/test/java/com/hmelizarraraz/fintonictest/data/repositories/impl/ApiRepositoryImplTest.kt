package com.hmelizarraraz.fintonictest.data.repositories.impl

import com.hmelizarraraz.fintonictest.data.models.beer.BeerModelItem
import com.hmelizarraraz.fintonictest.data.repositories.ConnectionApiService
import io.reactivex.Flowable

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ApiRepositoryImplTest {

    @Test
    fun `get beers`() {
        val apiService = mock(ConnectionApiService::class.java)
        val apiRepository = ApiRepositoryImpl(apiService)

        val list = ArrayList<BeerModelItem>()
        list.add(BeerModelItem())

        `when`(apiService.getBeers(10)).thenReturn(Flowable.fromArray(list))

        val subscriber = apiRepository.getBeers().test()
        subscriber.assertValueCount(1)
    }
}