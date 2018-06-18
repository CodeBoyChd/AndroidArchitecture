package com.sample.room.repository.database.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.sample.room.typeConverter.TypeConverter
import io.reactivex.annotations.NonNull
import java.util.*

@Entity(tableName = "events")
data class EventEntity(

        @PrimaryKey(autoGenerate = true)
        @NonNull
        val id: Int,

        val name: String,

        val description: String,

        @ColumnInfo(name = "date")
        val date: Date
)