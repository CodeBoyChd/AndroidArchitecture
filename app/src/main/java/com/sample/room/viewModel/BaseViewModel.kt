package com.sample.room.viewModel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.sample.room.repository.MyRepository
import com.sample.room.schedulers.BaseSchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel(internal val myRepository: MyRepository,
                             internal val schedulerProvider: BaseSchedulerProvider) : ViewModel() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()
    var isLoading: ObservableBoolean = ObservableBoolean(false)

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun setIsLoading(loading: Boolean) = isLoading.set(loading)
}

