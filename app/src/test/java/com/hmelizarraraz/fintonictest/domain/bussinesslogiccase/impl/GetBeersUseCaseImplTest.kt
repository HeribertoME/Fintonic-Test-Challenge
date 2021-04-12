package com.hmelizarraraz.fintonictest.domain.bussinesslogiccase.impl

import com.hmelizarraraz.fintonictest.data.manager.DataManager
import com.hmelizarraraz.fintonictest.data.mocks.ApiRepositoryMock
import com.hmelizarraraz.fintonictest.data.mocks.DataBaseRepositoryMock
import com.hmelizarraraz.fintonictest.domain.bussinesslogiccase.GetBeersUseCase
import io.reactivex.Flowable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetBeersUseCaseImplTest {

    private lateinit var mGetBeersUseCase: GetBeersUseCase

    @Mock
    private lateinit var mDataManager: DataManager

    private val mApiRepositoryMock = ApiRepositoryMock()

    private val mDatabaseRepositoryMock = DataBaseRepositoryMock()

    @Before
    fun setup() {
        mGetBeersUseCase = GetBeersUseCaseImpl(mDataManager)
    }

    @Test
    fun `when subscribe get list of beers`() {
        `when`(mDataManager.getApiRepository()).thenReturn(mApiRepositoryMock)
        `when`(mDataManager.getDBRepository()).thenReturn(mDatabaseRepositoryMock)

        mGetBeersUseCase
            .onSuccess {
                assertTrue(it.isEmpty())
            }
            .onError {
                fail()
            }
            .subscribe()

        mGetBeersUseCase.unSubscribe()

    }

}