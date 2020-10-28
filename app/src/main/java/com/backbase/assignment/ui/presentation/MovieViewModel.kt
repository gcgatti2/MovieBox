package com.backbase.assignment.ui.presentation

import androidx.lifecycle.ViewModel
import com.backbase.assignment.ui.data.MovieRepository
import com.backbase.assignment.ui.data.MovieRetrofitService
import com.backbase.assignment.ui.data.remote.MovieApi

class MovieViewModel: ViewModel() {

    private val movieRepository = MovieRepository(MovieRetrofitService.createService(MovieApi::class.java))

    companion object {
        const val CURRENTLY_PLAYING_MOVIES = "currently_playing_movies"
        const val POPULAR_MOVIES = "popular_movies"
    }

    fun getCurrentMoviesPlaying() = movieRepository.getCurrentlyPlayingMovies()

    fun getPopularMovies() = movieRepository.getPopularMovies()

    suspend fun getMovieDetailsById(id: Long) = movieRepository.getMovieDetailsById(id)
}