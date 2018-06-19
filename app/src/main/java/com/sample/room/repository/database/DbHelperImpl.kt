package com.sample.room.repository.database

import android.arch.lifecycle.LiveData
import com.sample.room.repository.database.entity.PopularMovieDTO
import io.reactivex.Completable
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

class DbHelperImpl @Inject constructor(val mAppDatabase: MyDatabase) : DbHelper {

    // add a popular movie in the database
    override fun addFavouriteMovie(popularMovieDTO: PopularMovieDTO): Completable {
        Timber.d("Add popular movie")
        return Completable.fromCallable {
            mAppDatabase.popularMoviesDao().addPopularMovie(popularMovieDTO)
        }
    }

    // remove a particular movie from db
    override fun removeFavouriteMovie(popularMovieDTO: PopularMovieDTO): Completable {
        Timber.d("Remove popular movie")
        return Completable.fromCallable {
            mAppDatabase.popularMoviesDao().deletePopularMovie(popularMovieDTO)
        }
    }

    // get all the favourite movies present in the database
    override fun getAllFavouriteMovies(): Observable<LiveData<List<PopularMovieDTO>>> {
        Timber.d("Get all popular movie")
        return Observable.fromCallable {
            mAppDatabase.popularMoviesDao().getAllPopularMovies()
        }
    }
}