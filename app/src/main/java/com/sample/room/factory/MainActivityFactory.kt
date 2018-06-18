package com.sample.room.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.sample.room.repository.MyRepository
import com.sample.room.schedulers.BaseSchedulerProvider
import com.sample.room.viewModel.MainActivityViewModel
import javax.inject.Inject

class MainActivityFactory @Inject constructor(private val myRepository: MyRepository, private val schedulerProvider: BaseSchedulerProvider)
    : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(myRepository, schedulerProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}