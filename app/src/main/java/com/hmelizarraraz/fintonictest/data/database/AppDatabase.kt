package com.hmelizarraraz.fintonictest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hmelizarraraz.fintonictest.data.database.daos.BeerDao
import com.hmelizarraraz.fintonictest.data.database.entities.BeerEntity

/**
 * AppDatabase
 */
@Database(
    entities = [BeerEntity::class],
    version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        /**
         * Constant for database name
         */
        const val DB_NAME = "fintonic-db"

    }

    /**
     * Method to get beer dao
     *
     * @return beer dao
     */
    abstract fun beerDao(): BeerDao

}