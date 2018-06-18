package com.sample.room.module

import com.sample.room.schedulers.BaseSchedulerProvider
import com.sample.room.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class SchedulerModule {

    @Provides
    internal fun provideSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProvider.getsInstance()
    }
}
