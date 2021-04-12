package com.hmelizarraraz.fintonictest.data.repositories.impl

import com.hmelizarraraz.fintonictest.data.mocks.DataBaseRepositoryMock
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataBaseRepositoryImplTest {

    @Test
    fun `should get beer dao`() {
        val beerDao = DataBaseRepositoryMock().beerDao()
        val databaseRepository = DataBaseRepositoryImpl(beerDao)
        assertNotNull(databaseRepository.beerDao())
    }

}