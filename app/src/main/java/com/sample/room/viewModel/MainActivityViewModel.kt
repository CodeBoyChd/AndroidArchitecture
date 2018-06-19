package com.sample.room.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.sample.room.repository.MyRepository
import com.sample.room.repository.database.entity.PopularMovieDTO
import com.sample.room.schedulers.BaseSchedulerProvider
import timber.log.Timber

class MainActivityViewModel(repository: MyRepository, schedulerProvider: BaseSchedulerProvider) :
        BaseViewModel(repository, schedulerProvider) {
    private val popularMoviesLiveData = MutableLiveData<ArrayList<PopularMovieDTO>>()

    //add favourite movie in the database
    fun addFavouriteMovie(popularMovieDTO: PopularMovieDTO) {
        Timber.d("Add favourite movie")
        compositeDisposable.add(myRepository
                .addFavouriteMovie(popularMovieDTO)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ Timber.d("onSuccess: Favourite movie added") },
                        { t -> Timber.d("Issue while adding in database: $t") }))
    }

    // remove favourite movie from database
    fun removeFavouriteMovie(popularMovieDTO: PopularMovieDTO) {
        Timber.d("Remove favourite movie")
        compositeDisposable.add(myRepository
                .removeFavouriteMovie(popularMovieDTO)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ Timber.d("onSuccess: Favourite movie removed") },
                        { t -> Timber.d("Issue while removing from database: $t") }))
    }

    // fetch the latest popular movies from server
    fun getPopularMoviesFromServer() {
        Timber.d("Get popular movies from server")

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