package com.backbase.assignment

import com.backbase.assignment.ui.data.MovieRepository
import com.backbase.assignment.ui.data.remote.MovieApi
import com.backbase.assignment.ui.presentation.util.Either
import com.backbase.assignment.ui.presentation.util.Failure
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import retrofit2.Response

class MovieRepositoryUnitTest {

    private val movieApi = mockk<MovieApi>()
    private val randomId = 1L
    private val defaultMovie = MovieTestBuilder().build()
    private val movieRepository = MovieRepository(movieApi)

    @Test
    fun testMovieResponse_getMovieId() {
        runBlocking {

            coEvery {
                movieApi.getMovieDetailsById(randomId)
            }.returns(Response.success(defaultMovie))
            var actual = movieRepository.getMovieDetailsById(randomId)
            actual shouldBeEqualTo Either.Right(defaultMovie)

            coEvery {
                movieApi.getMovieDetailsById(randomId)
            }.returns(Response.error(404, ResponseBody.create(null, "")))
            actual = movieRepository.getMovieDetailsById(randomId)
            actual shouldBeEqualTo Either.Left(Failure.ServerFailure(404))
        }
    }
}