package com.backbase.assignment.ui.data

import androidx.paging.PagingSource
import com.backbase.assignment.ui.data.remote.MovieApi
import com.backbase.assignment.ui.data.remote.entity.Movie
import com.backbase.assignment.ui.data.util.TMDB_STARTING_PAGE
import com.backbase.assignment.ui.data.util.NoConnectivityException
import com.backbase.assignment.ui.data.util.ServerException

class PopularMovieDataSource(private val movieApi: MovieApi): PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: TMDB_STARTING_PAGE
        try {
            val response = movieApi.getPopularMovies(position)
            if(response.isSuccessful && response.body() != null){
                val mappedResponse = response.body()!!.movies.map {
                    try {
                        val movieResponse = movieApi.getMovieDetailsById(it.id)
                        if(movieResponse.isSuccessful && movieResponse.body() != null){
                            return@map movieResponse.body()!!
                        } else {
                            throw ServerException()
                        }
                    } catch(e: NoConnectivityException) {
                        throw NoConnectivityException()
                    }
                }
                //if mappedResponse gets to this point without return then it successfully
                //mapped the entire list
                return LoadResult.Page(
                    data = mappedResponse,
                    prevKey = if (position == TMDB_STARTING_PAGE) null else position - 1,
                    nextKey = if (position == response.body()!!.totalPages) null else position + 1
                )
            } else {
                throw ServerException()
            }
        } catch(e: NoConnectivityException){
            return LoadResult.Error(NoConnectivityException())
        } catch(e: ServerException){
            return LoadResult.Error(ServerException())
        }
    }
}