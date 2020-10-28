package com.backbase.assignment

import com.backbase.assignment.ui.presentation.util.getHourAndMinuteFromMinute
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

import org.junit.Assert.*

/**
 * Unit Test to see whether to see if the getHourAndMinuteFromMinute function in
 * com.backbase.assignment.ui.presentation.util gives correct result
 */
class MinuteToHourMinuteUnitTest {
    @Test
    fun hourMinuteString_isCorrect() {
        var minutes = 120
        var expected = "2h"
        var actual = getHourAndMinuteFromMinute(minutes)
        expected shouldBeEqualTo actual

        minutes = 30
        expected = "30m"
        actual = getHourAndMinuteFromMinute(minutes)
        expected shouldBeEqualTo  actual

        minutes = 113
        expected = "1h 53m"
        actual = getHourAndMinuteFromMinute(minutes)
        expected shouldBeEqualTo  actual
    }
}
