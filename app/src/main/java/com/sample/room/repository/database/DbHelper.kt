package com.sample.room.repository.database

import android.arch.lifecycle.LiveData
import com.sample.room.repository.database.entity.EventEntity
import io.reactivex.Observable

interface DbHelper {

    fun getAllEvents(): Observable<LiveData<List<EventEntity>>>

    fun addEvent(event: EventEntity): Observable<Boolean>

    fun deleteEvent(event: EventEntity)

    fun deleteAllEvents()

    fun updateEvent(event: EventEntity)
}