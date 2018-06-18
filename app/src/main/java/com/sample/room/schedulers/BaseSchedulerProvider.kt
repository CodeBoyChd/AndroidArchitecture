package com.sample.room.schedulers

import io.reactivex.Scheduler

interface BaseSchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler

    fun network(): Scheduler

    fun background(): Scheduler

    fun trampoline(): Scheduler
}
