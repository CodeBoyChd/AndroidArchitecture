package com.sample.room.repository.network.response

import com.sample.room.repository.database.entity.PopularMovieDTO

data class PopularMoviesResponse(val page: Int,
                                 val total_pages: Int,
                                 val results: ArrayList<PopularMovieDTO>)

