package com.sample.room.repository.database

import android.arch.lifecycle.LiveData
import com.sample.room.repository.database.entity.EventEntity
import io.reactivex.Observable
import javax.inject.Inject

class DbHelperImpl @Inject constructor(val mAppDatabase: MyDatabase) : DbHelper {

    override fun getAllEvents(): Observable<LiveData<List<EventEntity>>> {
        return Observable.fromCallable {
            mAppDatabase.eventDao().getAllEvents()
        }
    }

    override fun addEvent(event: EventEntity): Observable<Boolean> {
        return Observable.fromCallable {
            mAppDatabase.eventDao().addEvent(event)
            true
        }
    }

    override fun deleteEvent(event: EventEntity) {
        Observable.fromCallable {
            mAppDatabase.eventDao().deleteEvent(event)
            true
        }
    }

    override fun deleteAllEvents() {
        Observable.fromCallable {
            mAppDatabase.eventDao().deleteAllEvents()
            true
        }
    }

    override fun updateEvent(event: EventEntity) {
    }
}