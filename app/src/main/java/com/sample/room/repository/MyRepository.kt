package com.sample.room.repository

import android.arch.lifecycle.LiveData
import com.sample.room.repository.database.DbHelper
import com.sample.room.repository.database.entity.PopularMovieDTO
import com.sample.room.repository.network.NetworkHelper
import com.sample.room.repository.network.response.PopularMoviesResponse
import com.sample.room.scope.ApplicationScope
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

/**
 * Repository for the application, having the instance of the database and network helper
 *  Using the dbhelper {@link DbHelper} for all the database queries and networkhelper
 *  {@link NetworkHelper} for the network related work
 */
@ApplicationScope
class MyRepository @Inject constructor(private val dbHelper: DbHelper,
                                       private val networkHelper: NetworkHelper) {

    // add favourite movie in database
    fun addFavouriteMovie(popularMovieDTO: PopularMovieDTO): Completable {
        Timber.d("Add favourite movie")
        return dbHelper.addFavouriteMovie(popularMovieDTO)
    }

    // remove favourite movie from database
    fun removeFavouriteMovie(popularMovieDTO: PopularMovieDTO): Completable {
        Timber.d("Remove favourite movie")
        return dbHelper.removeFavouriteMovie(popularMovieDTO)
    }

    // get all the favourite movies from database
    fun getFavouriteMoviesFromDB(): Observable<LiveData<List<PopularMovieDTO>>> {
        Timber.d("Get popular movies")
        return dbHelper.getAllFavouriteMovies()
    }

    // get popular movies data from server
    fun getPopularMovies(): Single<PopularMoviesResponse> {
        Timber.d("Get popular movies")
        return networkHelper.getPopularMovies()
    }
}