package com.backbase.assignment

import com.backbase.assignment.ui.data.remote.entity.BaseMovie

data class BaseMovieAndroidTestBuilder(
    private val id: Long = 1L,
    private val posterPath: String = "DEFAULT"
) {
    fun build() = BaseMovie(id, posterPath)
}