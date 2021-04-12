package com.hmelizarraraz.fintonictest.domain.bussinesslogiccase.impl

import com.hmelizarraraz.fintonictest.data.manager.DataManager
import com.hmelizarraraz.fintonictest.data.mocks.DataBaseRepositoryMock
import com.hmelizarraraz.fintonictest.domain.bussinesslogiccase.GetDetailUseCase
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Callable


@RunWith(MockitoJUnitRunner::class)
class GetDetailUseCaseImplTest {

    private lateinit var mGetDetailUseCase: GetDetailUseCase
    @Mock
    private lateinit var mDataManager: DataManager

    private val mDatabaseRepositoryMock = DataBaseRepositoryMock()

   @Before
   fun setup() {
       mGetDetailUseCase = GetDetailUseCaseImpl(mDataManager)
       RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
   }

    @Test
    fun `should get success response when subscribe`() {

        `when`(mDataManager.getDBRepository()).thenReturn(mDatabaseRepositoryMock)


        mGetDetailUseCase
            .onSuccess {
                assertNotNull(it)
                assertEquals(1, it.id)
            }
            .onError {
                fail()
            }
            .subscribe(1)

        mGetDetailUseCase.unSubscribe()
    }


}