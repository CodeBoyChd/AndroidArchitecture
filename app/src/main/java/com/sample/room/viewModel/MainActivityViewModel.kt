package com.sample.room.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.sample.room.repository.MyRepository
import com.sample.room.repository.database.entity.EventEntity
import com.sample.room.repository.network.response.PopularMovieDTO
import com.sample.room.schedulers.BaseSchedulerProvider
import timber.log.Timber

class MainActivityViewModel(repository: MyRepository, schedulerProvider: BaseSchedulerProvider) :
        BaseViewModel(repository, schedulerProvider) {
    private val eventLiveData = MutableLiveData<ArrayList<EventEntity>>()
    private val popularMoviesLiveData = MutableLiveData<ArrayList<PopularMovieDTO>>()

    /**
     * add event in the database
     */
    fun addEvent(eventEntity: EventEntity) {
        Timber.d("Add Event")
        compositeDisposable.add(myRepository
                .saveEvent(eventEntity)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ aBoolean -> Timber.d("Event has been saved") },
                        { throwable -> Timber.d("Event has not been saved") }))
    }

    // fetch the latest popular movies from server
    fun getPopularMoviesFromServer() {
        Timber.d("Get Popular Movies from Server")

        //send the data and
        compositeDisposable.add(myRepository
                .getPopularMovies()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ t ->
                    Timber.d("PopularMovies DTO: ${t.results}")
                    popularMoviesLiveData.postValue(t.results)
                }, { t -> Timber.d("Something went wrong") }))
    }

    // get the live data for the list of popular movies
    fun getPopularMoviesLiveData(): LiveData<ArrayList<PopularMovieDTO>> {
        return popularMoviesLiveData
    }
}