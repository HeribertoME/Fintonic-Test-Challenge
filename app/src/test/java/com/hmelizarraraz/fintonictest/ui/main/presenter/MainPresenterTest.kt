package com.hmelizarraraz.fintonictest.ui.main.presenter

import com.hmelizarraraz.fintonictest.data.models.AppError
import com.hmelizarraraz.fintonictest.data.models.presentation.BeerUIModel
import com.hmelizarraraz.fintonictest.ui.main.contract.MainContracts
import com.hmelizarraraz.fintonictest.ui.mocks.GetBeersUseCaseMock
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {

    private lateinit var mMainPresenter: MainContracts.IMainPresenter

    @Mock
    private lateinit var mMainView: MainContracts.IMainView

    private val mGetBeersUseCase = GetBeersUseCaseMock()

    @Before
    fun setup() {
        mMainPresenter = MainPresenter(mGetBeersUseCase)
        mMainPresenter.setView(mMainView)
    }

    @Test
    fun `should hide progress and update data when success response`() {
        mGetBeersUseCase.isSuccess = true

        val list = mutableListOf<BeerUIModel>()
        mGetBeersUseCase.beerList = list

        mMainPresenter.getBeerList()

        verify(mMainView).hideProgress()
        verify(mMainView).updateData(list)

        mMainPresenter.unSubscribe()
    }

    @Test
    fun `should hide progress and show error when error response`() {
        mGetBeersUseCase.isSuccess = false

        mMainPresenter.getBeerList()

        verify(mMainView).hideProgress()
        verify(mMainView).onError(AppError())

        mMainPresenter.unSubscribe()
    }


}