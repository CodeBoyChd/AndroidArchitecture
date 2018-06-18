package com.sample.room.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

/**
 * Provides different type of schedulers
 */
class SchedulerProvider private constructor() : BaseSchedulerProvider {

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun network(): Scheduler {
        return NETWORK_SCHEDULER
    }

    override fun background(): Scheduler {
        return BACKGROUND_SCHEDULER
    }

    override fun trampoline(): Scheduler {
        return Schedulers.trampoline()
    }

    companion object {

        private val NETWORK_SCHEDULER = Schedulers
                .from(Executors.newCachedThreadPool())
        private val BACKGROUND_SCHEDULER = Schedulers
                .from(Executors.newCachedThreadPool())

        private var sInstance: SchedulerProvider = SchedulerProvider()

        fun getsInstance(): SchedulerProvider {
            return sInstance
        }
    }
}
