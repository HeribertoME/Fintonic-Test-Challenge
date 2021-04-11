package com.hmelizarraraz.fintonictest.di.modules.data

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.Executors

/**
 * NetworkModule
 */
@Module
class NetworkModule {

    @Provides
    fun provideHttpClient(logger: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(logger)
        builder.cache(cache)
        return builder.build()
    }

    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    fun provideCache(file: File): Cache = Cache(file, 10 * 10 * 1000)

    @Provides
    fun provideCacheFile(context: Context): File = context.filesDir

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideRxAdapter(): RxJava2CallAdapterFactory {
        val internetScheduler = Schedulers.from(Executors.newCachedThreadPool())
        return RxJava2CallAdapterFactory.createWithScheduler(internetScheduler)
    }

}