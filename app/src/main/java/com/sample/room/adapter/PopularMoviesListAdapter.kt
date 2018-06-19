package com.sample.room.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.room.R
import com.sample.room.repository.network.response.PopularMovieDTO
import kotlinx.android.synthetic.main.popular_list_item.view.*
import timber.log.Timber

class PopularMoviesListAdapter(private var popularMoviesList: List<PopularMovieDTO>?,
                               private val context: Context)
    : RecyclerView.Adapter<PopularMoviesListAdapter.PopularMoviesViewHolder>() {

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        Timber.d("Binding view holder for position: ${position}")

        // put in data
        holder.movieName.text = popularMoviesList!![position].title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        return PopularMoviesViewHolder(LayoutInflater.from(context).inflate(R.layout.popular_list_item, parent, false))
    }

    // get item count
    override fun getItemCount(): Int = if (popularMoviesList == null) 0 else popularMoviesList!!.size

    // updating popular movies
    fun updatePopularMovies(list: ArrayList<PopularMovieDTO>?) {
        Timber.d("Data updated in adapter")
        popularMoviesList = list
        notifyDataSetChanged()
    }

    // view holder
    class PopularMoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieImageView = view.ivMovieImage
        val movieName = view.tvMovieName
        val movieFavouriteImageView = view.ivFavourite
    }
}