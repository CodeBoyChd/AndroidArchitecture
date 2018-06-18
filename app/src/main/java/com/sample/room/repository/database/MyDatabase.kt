package com.sample.room.repository.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.sample.room.repository.database.dao.EventDao
import com.sample.room.repository.database.entity.EventEntity
import com.sample.room.typeConverter.TypeConverter


// By default, to avoid poor UI performance, Room doesn't allow you
// to issue database queries on the main thread. LiveData applies this
// rule by automatically running the query asynchronously on a background
// thread, when needed. Room provides compile-time checks of SQLite statements.
// Your Room class must be abstract and extend RoomDatabase. Usually, you only
// need one instance of the Room database for the whole app.

// defintion for the database having the version and also the entities with type
// converters if required

@Database(entities = arrayOf(EventEntity::class), version = 1)
@TypeConverters(TypeConverter::class)
abstract class MyDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao
}