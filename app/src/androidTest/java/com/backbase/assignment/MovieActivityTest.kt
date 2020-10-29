package com.backbase.assignment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.backbase.assignment.ui.presentation.adapter.CurrentlyPlayingMoviePagingAdapter
import com.backbase.assignment.ui.presentation.screens.MovieActivity
import com.backbase.assignment.ui.presentation.util.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MovieActivity::class.java)

    @Before
    fun registerIdlingResource(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun clickOnCurrentlyPlayingMovieInRecyclerView_moveToDetailFragment() {
        //merely just checks that the data returned by getCurrentMoviesPlaying is loaded into a recyclerview and
        //one can click it and the fragment's layout is being displayed
        onView(withId(R.id.rv_currently_playing)).perform(RecyclerViewActions
                .actionOnItemAtPosition<CurrentlyPlayingMoviePagingAdapter.ViewHolder>(0, click()))
        onView(withId(R.id.movie_detail_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun clickOnPopularMovie_moveToDetailFragment() {
        onView(withId(R.id.rv_currently_playing))
            .perform(RecyclerViewActions.actionOnItemAtPosition<CurrentlyPlayingMoviePagingAdapter.ViewHolder>(0, click()))
        onView(withId(R.id.movie_detail_layout)).check(matches(isDisplayed()))
    }
}
