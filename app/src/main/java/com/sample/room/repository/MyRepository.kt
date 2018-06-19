package com.sample.room.repository

import android.arch.lifecycle.LiveData
import com.sample.room.repository.database.DbHelper
import com.sample.room.repository.database.entity.EventEntity
import com.sample.room.repository.network.NetworkHelper
import com.sample.room.repository.network.response.PopularMoviesResponse
import com.sample.room.scope.ApplicationScope
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

    /**
     * save the event in the database
     */
    fun saveEvent(eventEntity: EventEntity): Observable<Boolean> {
        return dbHelper.addEvent(eventEntity)
    }

    fun getAllEvents(): Observable<LiveData<List<EventEntity>>> {
        return dbHelper.getAllEvents()
    }

    /**
     * get popular mvovies data
     */
    fun getPopularMovies(): Single<PopularMoviesResponse> {
        Timber.d("Get popular movies")
        return networkHelper.getPopularMovies()
    }
}