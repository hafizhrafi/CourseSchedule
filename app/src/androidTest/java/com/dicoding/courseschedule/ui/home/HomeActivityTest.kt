package com.dicoding.courseschedule.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.intent.rule.IntentsRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import com.dicoding.courseschedule.R
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import com.dicoding.courseschedule.ui.add.AddCourseActivity


@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {

    @get:Rule
    var intentRule: IntentsRule = IntentsRule()

    @Before
    fun setup() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun testAddCourseActivity() {
        onView(withId(R.id.action_add)).perform(click())
        onView(withId(R.id.ed_course_name)).check(matches(isDisplayed()))
        onView(withId(R.id.spinner_day)).check(matches(isDisplayed()))
        onView(withId(R.id.ib_start_time)).check(matches(isDisplayed()))
        onView(withId(R.id.ib_end_time)).check(matches(isDisplayed()))
        onView(withId(R.id.ed_lecturer)).check(matches(isDisplayed()))
        onView(withId(R.id.ed_note)).check(matches(isDisplayed()))
        Intents.intended(IntentMatchers.hasComponent(AddCourseActivity::class.java.name))
    }
}