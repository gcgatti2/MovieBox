package com.backbase.assignment.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.backbase.assignment.ui.data.MovieRepository
import com.backbase.assignment.ui.data.MovieRetrofitService
import com.backbase.assignment.ui.data.remote.MovieApi

class MovieViewModel: ViewModel() {

    private val movieRepository = MovieRepository(MovieRetrofitService.createService(MovieApi::class.java))

    fun getCurrentMoviesPlaying() = movieRepository.getCurrentlyPlayingMovies()

    fun getPopularMovies() = movieRepository.getPopularMovies()

    fun getMovieDetailsById(id: Long) = liveData{
        emit(movieRepository.getMovieDetailsById(id))
    }
}