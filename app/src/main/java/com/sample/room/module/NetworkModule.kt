package com.sample.room.module

import com.databuddy.app.network.interceptor.MyAppHeaderInterceptor
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.sample.room.repository.network.interceptor.APILogger
import com.sample.room.scope.ApplicationScope
import com.sample.room.utility.BASE_URL
import com.sample.room.utility.CONNECTION_TIME_OUT
import com.sample.room.utility.READ_TIME_OUT
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    @ApplicationScope
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message -> Timber.i(message) }
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    @Provides
    @ApplicationScope
    fun providesOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT.toLong(), TimeUnit.SECONDS)
                .connectTimeout(CONNECTION_TIME_OUT.toLong(), TimeUnit.SECONDS)
                .addInterceptor(APILogger())
                .addInterceptor(interceptor)
                .addInterceptor(MyAppHeaderInterceptor())
                .build()
    }

    @Provides
    @ApplicationScope
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}
