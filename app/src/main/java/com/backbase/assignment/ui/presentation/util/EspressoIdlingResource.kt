package com.backbase.assignment.ui.presentation.util

import android.util.Log
import androidx.test.espresso.idling.CountingIdlingResource


object EspressoIdlingResource {

    private const val RESOURCE = "RESOURCE"

    @JvmField val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        Log.d("CountingIdlingResource", "incremented")
        countingIdlingResource.increment()
    }

    fun decrement() {
        Log.d("CountingIdlingResource", "decremented")
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}
