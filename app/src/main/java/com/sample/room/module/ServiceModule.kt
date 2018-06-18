package com.sample.room.module

import com.sample.room.repository.network.service.MovieBaseService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [(NetworkModule::class)])
class ServiceModule {

    @Provides
    fun provideMovieBaseService(retrofit: Retrofit): MovieBaseService {
        return retrofit.create(MovieBaseService::class.java)
    }
}
