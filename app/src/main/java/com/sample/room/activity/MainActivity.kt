package com.sample.room.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.sample.room.FavouriteIconClickListener
import com.sample.room.R
import com.sample.room.adapter.PopularMoviesListAdapter
import com.sample.room.factory.MainActivityFactory
import com.sample.room.repository.database.entity.PopularMovieDTO
import com.sample.room.viewModel.MainActivityViewModel
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity(), FavouriteIconClickListener {

    @Inject
    lateinit var mainActivityFactory: MainActivityFactory

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var adapter: PopularMoviesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // set up the layout
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        // set up view model
        mainActivityViewModel = ViewModelProviders.of(this, mainActivityFactory).get(MainActivityViewModel::class.java)
        mainActivityViewModel.getPopularMoviesLiveData().observe(this,
                Observer { t ->
                    Timber.d("Popular movies data has been updated")
                    adapter.updatePopularMovies(t)
                })

        // set up adapter
        adapter = PopularMoviesListAdapter(ArrayList(), this, this)

        // set up the recycler view
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        // get all the popular movies listing
        mainActivityViewModel.getPopularMoviesFromServer()
    }

    override fun onFavouriteIconClicked(makeFavourite: Boolean, popularMovieDTO: PopularMovieDTO) {
        Timber.d("Favourite Icon Clicked: Adding: $makeFavourite")

        if (makeFavourite) {
            mainActivityViewModel.addFavouriteMovie(popularMovieDTO)
        } else {
            mainActivityViewModel.removeFavouriteMovie(popularMovieDTO)
        }
    }
}

