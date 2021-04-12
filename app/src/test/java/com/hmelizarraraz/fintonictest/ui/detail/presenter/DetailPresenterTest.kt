package com.hmelizarraraz.fintonictest.ui.detail.presenter

import com.hmelizarraraz.fintonictest.data.database.entities.BeerEntity
import com.hmelizarraraz.fintonictest.data.models.AppError
import com.hmelizarraraz.fintonictest.ui.detail.contract.DetailContract
import com.hmelizarraraz.fintonictest.ui.mocks.GetDetailUseCaseMock
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailPresenterTest {

    private lateinit var mDetailPresenter: DetailContract.IDetailPresenter

    @Mock
    private lateinit var mDetailView: DetailContract.IDetailView

    private val mGetDetailUseCase = GetDetailUseCaseMock()

    @Before
    fun setup() {
        mDetailPresenter = DetailPresenter(mGetDetailUseCase)
        mDetailPresenter.setView(mDetailView)
    }

    @Test
    fun `should show details when success response`() {
        mGetDetailUseCase.isSuccess = true
        val beer = BeerEntity(1, "Name", false, "", "Desc")
        mGetDetailUseCase.beerEntity = beer
        mDetailPresenter.requestDetails(1)
        Mockito.verify(mDetailView).showDetails(beer)

        mDetailPresenter.unSubscribe()
    }

    @Test
    fun `should on error when error response`() {
        mGetDetailUseCase.isSuccess = false
        mDetailPresenter.requestDetails(1)
        Mockito.verify(mDetailView).onError(AppError())
    }

}