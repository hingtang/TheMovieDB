package com.example.eastagile.themoviedb.presentation.detail

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.eastagile.themoviedb.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailActivityTest{

    @get:Rule
    val activityRule = ActivityTestRule<DetailActivity>(DetailActivity::class.java)

    @Test
    fun should_display_toolbar_when_app_launched() {
        activityRule.launchActivity(Intent())

        Espresso.onView(ViewMatchers.withId(R.id.toolbarLogo)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun should_display_tab_bar_when_app_launched() {
        activityRule.launchActivity(Intent())

        Espresso.onView(ViewMatchers.withId(R.id.tabLayout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.viewPager)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
