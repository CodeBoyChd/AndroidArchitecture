package com.sample.room.repository.network

import com.sample.room.repository.network.response.PopularMoviesResponse
import com.sample.room.repository.network.service.MovieBaseService
import com.sample.room.utility.API_KEY
import io.reactivex.Single
import javax.inject.Inject

class MyNetworkHelper @Inject constructor(private val movieBaseService: MovieBaseService) : NetworkHelper {

    override fun getPopularMovies(): Single<PopularMoviesResponse> {
        return movieBaseService.getPopularMovies(API_KEY)
    }
}