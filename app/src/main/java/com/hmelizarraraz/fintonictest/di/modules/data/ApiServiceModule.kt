package com.hmelizarraraz.fintonictest.di.modules.data

import com.hmelizarraraz.fintonictest.BuildConfig
import com.hmelizarraraz.fintonictest.data.repositories.ConnectionApiService
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ApiServiceModule
 */
@Module
class ApiServiceModule {

    @Provides
    fun provideApiService(client: OkHttpClient, gson: GsonConverterFactory, rxAdapter: RxJava2CallAdapterFactory): ConnectionApiService {
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(gson)
            .addCallAdapterFactory(rxAdapter)
            .build()

        return retrofit.create(ConnectionApiService::class.java)
    }
}