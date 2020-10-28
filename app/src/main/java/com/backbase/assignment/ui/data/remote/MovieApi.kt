package com.backbase.assignment.ui.data.remote

import com.backbase.assignment.ui.data.remote.entity.Movie
import com.backbase.assignment.ui.data.remote.entity.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/now_playing")
    suspend fun getMoviesPlayingNow(@Query("page") page: Int): Response<MovieResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("page") page: Int): Response<MovieResponse>

    @GET("movie/{movieId}")
    suspend fun getMovieDetailsById(@Path("movieId") movieId: Long): Response<Movie>
}