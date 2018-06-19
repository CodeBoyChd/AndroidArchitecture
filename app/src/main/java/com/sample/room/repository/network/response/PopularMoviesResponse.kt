package com.sample.room.repository.network.response

 data class PopularMoviesResponse(val page: Int,
                                 val total_pages: Int,
                                 val results: ArrayList<PopularMovieDTO>)

data class PopularMovieDTO(val id: Long,
                           val video: Boolean,
                           val vote_count: Long,
                           val vote_average: String,
                           val title: String,
                           val poster_path: String,
                           val backdrop_path: String,
                           val adult: Boolean,
                           val overview: String,
                           val release_date: String)