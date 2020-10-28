package com.backbase.assignment

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ScrollToAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.backbase.assignment.ui.presentation.screens.MovieActivity
import com.backbase.assignment.ui.presentation.screens.MovieDetailFragment
import com.backbase.assignment.ui.presentation.util.getHourAndMinuteFromMinute
import com.backbase.assignment.ui.presentation.util.getLongformDate
import com.backbase.assignment.util.atPosition
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDetailFragmentTest {

    private val movie = MovieAndroidTestBuilder().build()

    @Test
    fun passMovie_LaunchFragment() {
        val fragmentArgs = Bundle().apply {
            putParcelable(MovieActivity.MOVIE_DETAILS, movie)
        }
        launchFragmentInContainer<MovieDetailFragment>(fragmentArgs, R.style.AppTheme)
        onView(withId(R.id.tv_movie_title)).check(matches(withText(movie.title)))
        onView(withId(R.id.tv_date_and_duration)).check(matches
            (withText(getLongformDate(movie.releaseDate) + " - " + getHourAndMinuteFromMinute(movie.duration))))
        onView(withId(R.id.tv_movie_description)).check(matches(withText(movie.overview)))
        for(i in movie.genres.indices) {
            onView(withId(R.id.rv_genre_list)).check(matches(atPosition(i, hasDescendant(withText(movie.genres[i].name)))))
        }
    }

    @Test
    fun passMovieId_LaunchFragment() {
        val fragmentArgs = Bundle().apply {
            putLong(MovieActivity.MOVIE_ID, movie.id)
        }
        launchFragmentInContainer<MovieDetailFragment>(fragmentArgs, R.style.AppTheme)
        SystemClock.sleep(5000)
        onView(withId(R.id.tv_movie_title)).check(matches(withText(movie.title)))
        onView(withId(R.id.tv_date_and_duration)).check(matches
            (withText(getLongformDate(movie.releaseDate) + " - " + getHourAndMinuteFromMinute(movie.duration))))
        onView(withId(R.id.tv_movie_description)).check(matches(withText(movie.overview)))
        for(i in movie.genres.indices) {
            onView(withId(R.id.rv_genre_list)).check(matches(atPosition(i, hasDescendant(withText(movie.genres[i].name)))))
        }
    }
}