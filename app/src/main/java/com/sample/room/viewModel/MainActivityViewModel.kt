package com.sample.room.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.sample.room.repository.MyRepository
import com.sample.room.repository.database.entity.EventEntity
import com.sample.room.schedulers.BaseSchedulerProvider
import timber.log.Timber

class MainActivityViewModel(repository: MyRepository, schedulerProvider: BaseSchedulerProvider) :
        BaseViewModel(repository, schedulerProvider) {
    private val eventLiveData = MutableLiveData<List<EventEntity>>()

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

    /**
     * get all the events from the database
     */
    fun getAllEvents() {
        Timber.d("get all events")
        compositeDisposable.add(myRepository.getAllEvents()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({ data ->
                    Timber.d("Received Data from DB")
                    eventLiveData.postValue(data.value)
                }, { t -> Timber.d("Error while getting events: %s", t.localizedMessage) }))
    }

    /**
     * get the events
     */
    fun getEvents(): LiveData<List<EventEntity>> {
        Timber.d("get events")
        return eventLiveData
    }

    fun getPopularMovies() {
        setIsLoading(true)
        compositeDisposable.add(myRepository
                .getPopularMovies()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe())
    }
}