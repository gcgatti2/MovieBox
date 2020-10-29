package com.backbase.assignment.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.backbase.assignment.ui.data.MovieRepository
import com.backbase.assignment.ui.data.MovieRetrofitService
import com.backbase.assignment.ui.data.remote.MovieApi
import org.jetbrains.annotations.TestOnly

open class MovieViewModel: ViewModel() {

    private var movieRepository = MovieRepository(MovieRetrofitService.createService(MovieApi::class.java))

    fun getMovieDetailsById(id: Long) = liveData{
        emit(movieRepository.getMovieDetailsById(id))
    }

    open fun getCurrentMoviesPlaying() = movieRepository.getCurrentlyPlayingMovies()

    fun getPopularMovies() = movieRepository.getPopularMovies()
}