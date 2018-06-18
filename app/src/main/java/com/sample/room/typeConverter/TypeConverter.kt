package com.sample.room.typeConverter

import android.arch.persistence.room.TypeConverter
import java.util.*

class TypeConverter {

    @TypeConverter
    fun fromTimeStamp(value: Long): Date = Date(value)

    @TypeConverter
    fun dateToTimeStamp(date: Date): Long = date.time
}