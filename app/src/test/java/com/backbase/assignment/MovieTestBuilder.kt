package com.backbase.assignment

import com.backbase.assignment.ui.data.remote.entity.Genre
import com.backbase.assignment.ui.data.remote.entity.Movie

data class MovieTestBuilder(
    private val movieId: Long = DEFAULT_MOVIE_ID,
    private val movieTitle: String = DEFAULT_MOVIE_TITLE,
    private val movieOverview: String = DEFAULT_MOVIE_OVERVIEW,
    private val moviePosterPath: String = DEFAULT_POSTER_PATH,
    private val movieAvgRating: Double = DEFAULT_AVG_RATING,
    private val movieReleaseDate: String = DEFAULT_RELEASE_DATE,
    private val movieGenreList: List<Genre> = DEFAULT_GENRE_LIST,
    private val movieDuration: Int = DEFAULT_DURATION
) {
    fun build() = Movie(movieId, movieTitle, movieOverview, moviePosterPath,
        movieAvgRating, movieReleaseDate, movieGenreList, movieDuration)
    
    companion object {
        const val DEFAULT_MOVIE_ID = 1L
        const val DEFAULT_MOVIE_TITLE = "DEFAULT"
        const val DEFAULT_MOVIE_OVERVIEW = "OVERVIEW"
        const val DEFAULT_POSTER_PATH = "PATH"
        const val DEFAULT_AVG_RATING = 0.0
        const val DEFAULT_RELEASE_DATE = "DATE"
        val DEFAULT_GENRE_LIST = listOf(Genre("Action"), Genre("Science Fiction"))
        const val DEFAULT_DURATION = 60
    }
}