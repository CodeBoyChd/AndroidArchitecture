package com.sample.room.repository.database.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.sample.room.repository.database.entity.EventEntity
import java.util.*

// creating a dao which can be an interface or abstract class
// functions will be provided @Insert, @Query, @Update
// Also used the Live data: which is a lifecycle library class for data
// observation, can help your app respond to data changes. If you use a
// return value of type LiveData in your method description, Room generates
// all necessary code to update the LiveData when the database is updated.

@Dao
interface EventDao {

    @Query("SELECT * FROM events WHERE date>:minDate")
    fun getEvents(minDate: Date): LiveData<List<EventEntity>>

    @Insert(onConflict = REPLACE)
    fun addEvent(eventEntity: EventEntity)

    @Delete
    fun deleteEvent(eventEntity: EventEntity)

    @Update(onConflict = REPLACE)
    fun updateEvent(eventEntity: EventEntity)

    @Query("DELETE FROM events")
    fun deleteAllEvents()

    @Query("SELECT * FROM events ORDER BY name ASC")
    fun getAllEvents(): LiveData<List<EventEntity>>
}