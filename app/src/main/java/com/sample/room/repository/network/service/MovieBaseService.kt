package com.sample.room.repository.network.service

import com.sample.room.repository.network.response.PopularMoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieBaseService {

    @GET("movie/popular/")
    fun getPopularMovies(@Query("api_key") apiKey: String): Single<PopularMoviesResponse>
}