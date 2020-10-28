package com.backbase.assignment.ui.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.backbase.assignment.ui.data.remote.MovieApi

class MovieRepository(private val movieApi: MovieApi) {

    companion object {
        const val TMDB_PAGE_SIZE = 20
    }

    fun getCurrentlyPlayingMovies() =
        Pager(
            config = PagingConfig(pageSize = TMDB_PAGE_SIZE),
            pagingSourceFactory = { CurrentlyPlayingMovieDataSource(movieApi) }
        ).flow

    fun getPopularMovies() =
        Pager(
            config = PagingConfig(pageSize = TMDB_PAGE_SIZE),
            pagingSourceFactory = { PopularMovieDataSource(movieApi) }
        ).flow

    suspend fun getMovieDetailsById(id: Long) = movieApi.getMovieDetailsById(id)
}