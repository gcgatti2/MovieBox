package com.backbase.assignment.ui.presentation.screens

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.backbase.assignment.R
import com.backbase.assignment.databinding.ActivityMovieBinding
import com.backbase.assignment.ui.data.remote.entity.Movie
import com.backbase.assignment.ui.presentation.MovieViewModel
import com.backbase.assignment.ui.presentation.adapter.CurrentlyPlayingMoviePagingAdapter
import com.backbase.assignment.ui.presentation.adapter.CurrentlyPlayingMoviePagingAdapter.CurrentPlayingMovieListener
import com.backbase.assignment.ui.presentation.adapter.PopularMovieAdapter
import com.backbase.assignment.ui.presentation.adapter.PopularMovieAdapter.PopularMovieListener
import com.backbase.assignment.ui.presentation.util.EspressoIdlingResource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieActivity : AppCompatActivity(), CurrentPlayingMovieListener, PopularMovieListener {

    companion object {
        const val MOVIE_ID = "movie_id"
        const val MOVIE_DETAILS = "movie_details"
    }

    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: ActivityMovieBinding
    private lateinit var currentlyPlayingMoviePagerAdapter: CurrentlyPlayingMoviePagingAdapter
    private lateinit var popularMovieAdapter: PopularMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        initRecyclerviews()

        collectCurrentlyPlayingMovies()
        currentlyPlayingMoviePagerAdapter.addLoadStateListener { loadState->
            if(loadState.refresh is LoadState.NotLoading){
                EspressoIdlingResource.decrement()
            }
        }
        collectPopularMovies()
        popularMovieAdapter.addLoadStateListener { loadState->
            if(loadState.refresh is LoadState.NotLoading){
                EspressoIdlingResource.decrement()
            }
        }
    }

    private fun collectCurrentlyPlayingMovies(){
        EspressoIdlingResource.increment()
        lifecycleScope.launch {
            movieViewModel.getCurrentMoviesPlaying().collectLatest {
                currentlyPlayingMoviePagerAdapter.submitData(it)
            }
        }
    }

    private fun collectPopularMovies(){
        EspressoIdlingResource.increment()
        lifecycleScope.launch{
            movieViewModel.getPopularMovies().collectLatest {
                popularMovieAdapter.submitData(it)
            }
        }
    }

    override fun onPopularMovieClicked(movie: Movie) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MovieDetailFragment::class.java,
                Bundle().apply { putParcelable(MOVIE_DETAILS, movie) })
            .addToBackStack(null)
            .commit()
    }

    override fun onCurrentPlayingMovieClicked(id: Long) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, MovieDetailFragment::class.java,
                Bundle().apply { putLong(MOVIE_ID, id) })
            .addToBackStack(null)
            .commit()
    }

    private fun initRecyclerviews(){

        currentlyPlayingMoviePagerAdapter = CurrentlyPlayingMoviePagingAdapter(this)
        popularMovieAdapter = PopularMovieAdapter(this)

        binding.rvCurrentlyPlaying.apply {
            layoutManager = LinearLayoutManager(this@MovieActivity,
                LinearLayoutManager.HORIZONTAL, false)
            adapter = currentlyPlayingMoviePagerAdapter
        }
        binding.rvPopularMovies.apply {
            layoutManager = LinearLayoutManager(this@MovieActivity,
                LinearLayoutManager.VERTICAL, false)
            adapter = popularMovieAdapter
        }
    }
}
