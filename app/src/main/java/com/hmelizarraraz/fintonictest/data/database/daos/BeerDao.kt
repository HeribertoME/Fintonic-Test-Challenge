package com.hmelizarraraz.fintonictest.data.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hmelizarraraz.fintonictest.data.database.entities.BeerEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Data access class Beer Model.
 * Insert, update, select, delete operation/query using RxJava so thread
 * scheduling, non block ui operation can be maintained easily.
 */
@Dao
interface BeerDao {

    @Query("SELECT * FROM beers")
    fun loadBeers(): Single<List<BeerEntity>>

    @Query("SELECT * from beers where id = :id LIMIT 1")
    fun loadFlightEventById(id: Int): Single<BeerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBeers(flights: List<BeerEntity>): Completable

    @Query("DELETE FROM beers")
    fun deleteAllBeers(): Completable

}