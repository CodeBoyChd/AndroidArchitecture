package com.sample.room.repository.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "popular_movies")
data class PopularMovieDTO(
        @PrimaryKey
        @NotNull
        val id: Long,
        val video: Boolean,
        val vote_count: Long,
        val vote_average: String,
        val title: String,
        val poster_path: String,
        val backdrop_path: String,
        val adult: Boolean,
        val overview: String,
        val release_date: String,
        var favourite: Boolean)