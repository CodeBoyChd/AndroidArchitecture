package com.sample.room.module

import com.sample.room.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * We map all our activities here. And Dagger know our activities in compile time
 */

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}