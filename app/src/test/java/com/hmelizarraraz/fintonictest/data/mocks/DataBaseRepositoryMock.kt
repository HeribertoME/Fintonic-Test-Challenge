package com.hmelizarraraz.fintonictest.data.mocks

import com.hmelizarraraz.fintonictest.data.database.daos.BeerDao
import com.hmelizarraraz.fintonictest.data.database.entities.BeerEntity
import com.hmelizarraraz.fintonictest.data.repositories.DataBaseRepository
import io.reactivex.Completable
import io.reactivex.Single

class DataBaseRepositoryMock : DataBaseRepository {

    override fun beerDao(): BeerDao =
        object : BeerDao {
            override fun loadBeers(): Single<List<BeerEntity>> = Single.just(listOf())

            override fun loadFlightEventById(id: Int): Single<BeerEntity> = Single.just(
                BeerEntity(1,
                    "name",
                    false,
                    "http://www.images.com",
                    "Some description")
            )

            override fun insertBeers(flights: List<BeerEntity>): Completable = Completable.complete()

            override fun deleteAllBeers(): Completable = Completable.complete()

        }

}
