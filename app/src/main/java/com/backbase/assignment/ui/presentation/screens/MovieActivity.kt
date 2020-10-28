package com.backbase.assignment.ui.presentation.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.backbase.assignment.databinding.ActivityMovieBinding
import com.backbase.assignment.ui.presentation.MovieViewModel
import com.backbase.assignment.ui.presentation.adapter.CurrentlyPlayingMoviePagingAdapter
import com.backbase.assignment.ui.presentation.adapter.PopularMovieAdapter
import com.backbase.assignment.ui.util.MovieClickListener
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieActivity : AppCompatActivity(), MovieClickListener {

    companion object {
        val MOVIE_ID = "movie_id"
    }

    private val movieViewModel: MovieViewModel by viewModels()
    private lateinit var binding: ActivityMovieBinding
    private lateinit var currentlyPlayingMoviePagerAdapter: CurrentlyPlayingMoviePagingAdapter
    private lateinit var popularMovieAdapter: PopularMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentlyPlayingMoviePagerAdapter = CurrentlyPlayingMoviePagingAdapter(this)
        popularMovieAdapter = PopularMovieAdapter(this)
        initRecyclerviews()

        lifecycleScope.launch {
            movieViewModel.getCurrentMoviesPlaying().collectLatest {
                currentlyPlayingMoviePagerAdapter.submitData(it)
            }
        }

        lifecycleScope.launch{
            movieViewModel.getPopularMovies().collectLatest {
                popularMovieAdapter.submitData(it)
            }
        }
    }

    override fun onMovieClicked(id: Long) {
        //move to detail fragment
    }

    fun initRecyclerviews(){
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
