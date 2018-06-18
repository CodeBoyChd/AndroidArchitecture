package com.sample.room.repository.network

import com.sample.room.repository.network.response.PopularMoviesResponse
import io.reactivex.Single

interface NetworkHelper {

    fun getPopularMovies(): Single<PopularMoviesResponse>
}