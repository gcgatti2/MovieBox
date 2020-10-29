package com.backbase.assignment.ui.presentation

import androidx.lifecycle.*
import com.backbase.assignment.ui.data.MovieRepository
import com.backbase.assignment.ui.data.MovieRetrofitService
import com.backbase.assignment.ui.data.remote.MovieApi
import com.backbase.assignment.ui.data.remote.entity.Movie
import com.backbase.assignment.ui.presentation.screens.MovieActivity
import com.backbase.assignment.ui.presentation.util.Either
import com.backbase.assignment.ui.presentation.util.Failure
import kotlinx.coroutines.launch

class MovieViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {

    private val movieRepository = MovieRepository(MovieRetrofitService.createService(MovieApi::class.java))

    lateinit var currentMovieLiveData: MutableLiveData<Either<Failure, Movie>?>

    fun setCurrentMovieLiveDataFromSavedStateHandle(id: Long) {
        //the returned live data could have a value of null, in which case the fragment will make a network request
        currentMovieLiveData = savedStateHandle.getLiveData(id.toString())
    }

    fun getMovieDetailsById(id: Long){
        viewModelScope.launch {
            val value = movieRepository.getMovieDetailsById(id)
            currentMovieLiveData.postValue(value)
        }
    }

    fun getCurrentMoviesPlaying() = movieRepository.getCurrentlyPlayingMovies()

    fun getPopularMovies() = movieRepository.getPopularMovies()
}