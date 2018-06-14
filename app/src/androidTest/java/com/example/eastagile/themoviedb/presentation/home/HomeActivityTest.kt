package com.example.eastagile.themoviedb.presentation.home

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.eastagile.themoviedb.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest{

    @get:Rule
    private val activityRule = ActivityTestRule<HomeActivity>(HomeActivity::class.java)

    @Test
    fun should_display_toolbar_when_app_launched() {
        activityRule.launchActivity(Intent())

        onView(withId(R.id.toolbarLogo)).check(matches(isDisplayed()))
    }

    @Test
    fun should_display_tab_bar_when_app_launched() {
        activityRule.launchActivity(Intent())

        onView(withId(R.id.tabLayout)).check(matches(isDisplayed()))
        onView(withId(R.id.viewPager)).check(matches(isDisplayed()))
    }
}