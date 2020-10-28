package com.backbase.assignment

import android.os.SystemClock
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.backbase.assignment.ui.presentation.adapter.CurrentlyPlayingMoviePagingAdapter
import com.backbase.assignment.ui.presentation.screens.MovieActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieActivityTest {

    private lateinit var scenario: ActivityScenario<MovieActivity>

    @Test
    fun clickOnCurrentlyPlayingMovie_moveToDetailFragment() {
        scenario = launchActivity()
        SystemClock.sleep(5000)
        onView(withId(R.id.rv_currently_playing))
            .perform(RecyclerViewActions.actionOnItemAtPosition<CurrentlyPlayingMoviePagingAdapter.ViewHolder>(0, click()))
        onView(withId(R.id.movie_detail_layout)).check(matches(isDisplayed()))
        scenario.close()
    }

    @Test
    fun clickOnPopularMovie_moveToDetailFragment() {
        scenario = launchActivity()
        SystemClock.sleep(10000)
        onView(withId(R.id.rv_currently_playing))
            .perform(RecyclerViewActions.actionOnItemAtPosition<CurrentlyPlayingMoviePagingAdapter.ViewHolder>(0, click()))
        onView(withId(R.id.movie_detail_layout)).check(matches(isDisplayed()))
        scenario.close()
    }
}
