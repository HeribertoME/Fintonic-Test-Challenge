package com.hmelizarraraz.fintonictest.domain.helper

import com.hmelizarraraz.fintonictest.data.database.entities.BeerEntity
import org.junit.Assert.*
import org.junit.Test

class BeerPresentationHelperTest {

    @Test
    fun `should return beer ui model list`() {
        val listEntity = mutableListOf<BeerEntity>()

        listEntity.add(BeerEntity(1, "Name", false, "", "Desc 1"))
        listEntity.add(BeerEntity(2, "Name 2", false, "", "Desc 2"))

        val beerUIModel = BeerPresentationHelper().getBeerList(listEntity)

        assertEquals(1, beerUIModel[0].id)
        assertEquals(2, beerUIModel[1].id)

    }

}