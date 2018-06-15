package com.example.eastagile.themoviedb.presentation.home.popular

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.example.eastagile.themoviedb.R
import com.example.eastagile.themoviedb.test.TestFragmentActivity
import com.example.eastagile.themoviedb.utils.IdlingResourceViewHelper
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test

class PopularFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule<TestFragmentActivity>(TestFragmentActivity::class.java)

    lateinit var presenter: PopularPresenter

    private val screen = PopularScreen()

    @Test
    fun should_show_progress_bar_and_hide_recycler_view_when_getting_movie_data() {
        screen.start()

        screen.apply {
            screen.showProgressBar()
        }
    }

    @Test
    fun should_hide_progress_bar_and_show_recycler_view_when_got_movie_data() {
        screen.start()

        screen.apply {
            screen.hideProgressBar()
        }
    }

    @Test
    fun should_show_error_dialog_when_load_data_error() {
        screen.start()

        screen.apply {
            screen.showErrorDialog()
        }
    }

    @Test
    fun should_hide_error_dialog_when_load_data_success() {
        screen.start()

        screen.apply {
            screen.hideDialog()
        }
    }

    inner class PopularScreen {
        private lateinit var fragment: PopularFragment

        fun start() {
            activityRule.launchActivity(Intent())
            fragment = PopularFragment.newInstance() as PopularFragment
            activityRule.activity.setFragment(fragment)
            waitForFragmentAttach()

            presenter = fragment.presenter as PopularPresenter
        }

        fun showProgressBar() {
            runOnUIThread {
                fragment.showProgressBar()
            }

            onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
            onView(withId(R.id.recyclerView)).check(matches(not(isDisplayed())))
        }

        fun hideProgressBar() {
            runOnUIThread {
                fragment.hideProgressBar()
            }

            onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
            onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        }

        fun showErrorDialog() {
            runOnUIThread {
                fragment.showErrorDialog(ERROR_MESSAGE)
            }

            onView(withText(R.string.error)).check(matches(isDisplayed()))
        }

        fun hideDialog() {
            runOnUIThread {
                fragment.hideErrorDialog()
            }

            onView(withText(R.string.error)).check(doesNotExist())
        }

        fun stop() {
            activityRule.finishActivity()
        }

        private fun waitForFragmentAttach() {
            IdlingResourceViewHelper.waitViewDisplayed(R.id.container)
                    .on(activityRule.activity.findViewById(R.id.container))
        }

        private fun runOnUIThread(runnable: (() -> Unit)) {
            activityRule.runOnUiThread {
                runnable()
            }
        }
    }

    companion object {
        private const val ERROR_MESSAGE = "Error"
    }

}