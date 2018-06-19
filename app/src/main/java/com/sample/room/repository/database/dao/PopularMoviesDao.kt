package com.sample.room.repository.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.sample.room.repository.database.entity.PopularMovieDTO

// creating a dao which can be an interface or abstract class
// functions will be provided @Insert, @Query, @Update
// Also used the Live data: which is a lifecycle library class for data
// observation, can help your app respond to data changes. If you use a
// return value of type LiveData in your method description, Room generates
// all necessary code to update the LiveData when the database is updated.

@Dao
interface PopularMoviesDao {

    @Query("SELECT * FROM popular_movies")
    fun getAllPopularMovies(): LiveData<List<PopularMovieDTO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPopularMovie(popularMovieDTO: PopularMovieDTO)

    @Delete
    fun deletePopularMovie(popularMovieDTO: PopularMovieDTO)
}