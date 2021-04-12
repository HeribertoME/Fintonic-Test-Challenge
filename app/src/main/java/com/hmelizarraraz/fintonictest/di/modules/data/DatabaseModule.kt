package com.hmelizarraraz.fintonictest.di.modules.data

import android.content.Context
import androidx.room.Room
import com.hmelizarraraz.fintonictest.data.database.AppDatabase
import com.hmelizarraraz.fintonictest.data.database.daos.BeerDao
import dagger.Module
import dagger.Provides

/**
 * DatabaseModule
 */
@Module
class DatabaseModule {

    @Provides
    fun provideRoomDatabase(context: Context): AppDatabase =
        Room
            .databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideBeerDao(database: AppDatabase): BeerDao = database.beerDao()
}