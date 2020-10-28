package com.backbase.assignment.ui.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.backbase.assignment.ui.data.remote.MovieApi
import com.backbase.assignment.ui.data.remote.entity.Movie
import com.backbase.assignment.ui.util.Either
import com.backbase.assignment.ui.util.Failure
import com.backbase.assignment.ui.util.NoConnectivityException

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

    suspend fun getMovieDetailsById(id: Long): Either<Failure, Movie> {
        return try {
            val response = movieApi.getMovieDetailsById(id)
            if(response.isSuccessful && response.body() != null){
                Either.Right(response.body()!!)
            } else {
                Either.Left(Failure.ServerFailure(response.code()))
            }
        } catch(e: NoConnectivityException){
            Either.Left(Failure.ConnectionFailure)
        }
    }
}