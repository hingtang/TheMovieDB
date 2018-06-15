package com.example.eastagile.themoviedb.presentation.detail.detailTab

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import com.example.eastagile.themoviedb.R
import com.example.eastagile.themoviedb.data.Movie
import com.example.eastagile.themoviedb.presentation.home.popular.PopularFragmentTest
import com.example.eastagile.themoviedb.presentation.home.popular.PopularPresenter
import com.example.eastagile.themoviedb.test.TestFragmentActivity
import com.example.eastagile.themoviedb.utils.IdlingResourceViewHelper
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test

class DetailFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule<TestFragmentActivity>(TestFragmentActivity::class.java)

    lateinit var presenter: PopularPresenter

    private val screen = DetailScreen()

    @Test
    fun should_show_progress_bar_and_hide_recycler_view_when_getting_movie_data() {
        screen.start()

        screen.apply {
            screen.showProgressBar()
            screen.verifyProgressBarShown()
        }
    }

    @Test
    fun should_hide_progress_bar_and_show_recycler_view_when_got_movie_data() {
        screen.start()

        screen.apply {
            screen.hideProgressBar()
            verifyProgressBarHidden()
        }
    }

    @Test
    fun should_show_error_dialog_when_load_data_error() {
        screen.start()

        screen.apply {
            screen.showErrorDialog()
            verifyErrorDialogShown()
        }
    }

    @Test
    fun should_hide_error_dialog_when_load_data_success() {
        screen.start()

        screen.apply {
            screen.hideDialog()
            verifyErrorDialogHidden()
        }
    }

    inner class DetailScreen {
        private lateinit var fragment: DetailFragment

        fun start() {
            activityRule.launchActivity(Intent())
            fragment = DetailFragment.newInstance(Movie(MOVIE_ID, title = MOVIE_TITLE)) as DetailFragment
            activityRule.activity.setFragment(fragment)
            waitForFragmentAttach()

            presenter = fragment.presenter as PopularPresenter
        }

        fun showProgressBar() {
            runOnUIThread {
                fragment.showProgressBar()
            }
        }

        fun verifyProgressBarShown() {
            Espresso.onView(ViewMatchers.withId(R.id.progressBar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            Espresso.onView(ViewMatchers.withId(R.id.recyclerView)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
        }


        fun hideProgressBar() {
            runOnUIThread {
                fragment.hideProgressBar()
            }
        }

        fun verifyProgressBarHidden() {
            Espresso.onView(ViewMatchers.withId(R.id.progressBar)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
            Espresso.onView(ViewMatchers.withId(R.id.recyclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

        fun showErrorDialog() {
            runOnUIThread {
                fragment.showErrorDialog(ERROR_MESSAGE)
            }
        }

        fun verifyErrorDialogShown() {
            Espresso.onView(ViewMatchers.withText(R.string.error)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

        fun hideDialog() {
            runOnUIThread {
                fragment.hideErrorDialog()
            }
        }

        fun verifyErrorDialogHidden() {
            Espresso.onView(ViewMatchers.withText(R.string.error)).check(ViewAssertions.doesNotExist())
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
        private const val MOVIE_ID = 1
        private const val MOVIE_TITLE = "Title"
        private const val ERROR_MESSAGE = "Error"
    }
}
