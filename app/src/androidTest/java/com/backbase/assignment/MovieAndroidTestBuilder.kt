package com.backbase.assignment

import com.backbase.assignment.ui.data.remote.entity.Genre
import com.backbase.assignment.ui.data.remote.entity.Movie

data class MovieAndroidTestBuilder(
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
        const val DEFAULT_MOVIE_ID = 724989L
        const val DEFAULT_MOVIE_TITLE = "Hard Kill"
        const val DEFAULT_MOVIE_OVERVIEW = "The work of billionaire tech CEO Donovan Chalmers is so valuable that he hires mercenaries to protect it, and a terrorist group kidnaps his daughter just to get it."
        const val DEFAULT_POSTER_PATH = "PATH"
        const val DEFAULT_AVG_RATING = 4.2
        const val DEFAULT_RELEASE_DATE = "2020-10-23"
        val DEFAULT_GENRE_LIST = listOf(Genre("Action"), Genre("Thriller"))
        const val DEFAULT_DURATION = 98
    }
}