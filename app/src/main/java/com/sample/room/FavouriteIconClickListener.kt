package com.sample.room

import com.sample.room.repository.database.entity.PopularMovieDTO

interface FavouriteIconClickListener {

    fun onFavouriteIconClicked(isFavourite: Boolean, popularMovieDTO: PopularMovieDTO)
}