package com.sample.room.repository.database

import android.arch.lifecycle.LiveData
import com.sample.room.repository.database.entity.PopularMovieDTO
import io.reactivex.Completable
import io.reactivex.Observable

interface DbHelper {

    fun addFavouriteMovie(popularMovieDTO: PopularMovieDTO): Completable

    fun removeFavouriteMovie(popularMovieDTO: PopularMovieDTO): Completable

    fun getAllFavouriteMovies(): Observable<LiveData<List<PopularMovieDTO>>>
}