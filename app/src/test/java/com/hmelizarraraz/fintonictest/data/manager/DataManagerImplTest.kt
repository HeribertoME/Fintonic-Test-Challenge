package com.hmelizarraraz.fintonictest.data.manager

import com.hmelizarraraz.fintonictest.data.mocks.ApiRepositoryMock
import com.hmelizarraraz.fintonictest.data.mocks.DataBaseRepositoryMock
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DataManagerImplTest {

    private var mDataManager: DataManager? = null

    @Before
    fun setup() {
        mDataManager = DataManagerImpl(
            ApiRepositoryMock(),
            DataBaseRepositoryMock()
        )
    }

    @Test
    fun `should return api repository`() {
        val apiRepository = mDataManager!!.getApiRepository()
        Assert.assertNotNull(apiRepository)
    }

    @Test
    fun `should return database repository`() {
        val databaseRepository = mDataManager!!.getDBRepository()
        Assert.assertNotNull(databaseRepository)
    }

}