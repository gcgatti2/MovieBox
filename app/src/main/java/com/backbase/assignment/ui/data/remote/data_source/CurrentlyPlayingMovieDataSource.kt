package com.backbase.assignment.ui.data.remote.data_source

import androidx.paging.PagingSource
import com.backbase.assignment.ui.data.remote.MovieApi
import com.backbase.assignment.ui.data.remote.entity.BaseMovie
import com.backbase.assignment.ui.data.util.TMDB_STARTING_PAGE
import com.backbase.assignment.ui.data.util.NoConnectivityException
import com.backbase.assignment.ui.data.util.ServerException

class CurrentlyPlayingMovieDataSource(private val movieApi: MovieApi) : PagingSource<Int, BaseMovie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BaseMovie> {
        val position = params.key ?: TMDB_STARTING_PAGE
        return try {
            val response = movieApi.getMoviesPlayingNow(position)
            if (response.isSuccessful && response.body() != null) {
                LoadResult.Page(
                    data = response.body()!!.movies,
                    prevKey = if (position == TMDB_STARTING_PAGE) null else position - 1,
                    nextKey = if (position == response.body()!!.totalPages) null else position + 1
                )
            } else {
                LoadResult.Error(ServerException())
            }
        } catch (exception: NoConnectivityException) {
            LoadResult.Error(exception)
        }
    }
}