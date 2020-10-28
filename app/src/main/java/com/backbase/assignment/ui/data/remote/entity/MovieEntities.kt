package com.backbase.assignment.ui.data.remote.entity

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class MovieResponse(
    @Json(name = "results")
    val movies: List<BaseMovie>,
    @Json(name = "total_pages")
    val totalPages: Int
)

//minimum data needed to display the horizontal currently playing movies. need to get ID to fetch details regarding the
//vertical scrolling list as this needs the runtime of the movie, which isn't available in the popular/current movie endpoints
data class BaseMovie(
    @Json(name = "id")
    val id: Long,
    @Json(name = "poster_path")
    val posterPath: String?
)

@Parcelize
data class Movie(
    @Json(name = "id")
    val id: Long,
    @Json(name = "title")
    val title: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "vote_average")
    val averageRating: Double,
    @Json(name ="release_date")
    val releaseDate: String,
    @Json(name = "genres")
    val genres: List<Genre>,
    @Json(name = "runtime") //this is only in the more detailed endpoint where you fetch by movie id
    val duration: Int
): Parcelable