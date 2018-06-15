package com.example.eastagile.themoviedb.presentation.home.mostrated

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import com.example.eastagile.themoviedb.R
import com.example.eastagile.themoviedb.test.TestFragmentActivity
import com.example.eastagile.themoviedb.utils.IdlingResourceViewHelper
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test

class MostRatedFragmentTest {

    @get:Rule
    val activityRule = ActivityTestRule<TestFragmentActivity>(TestFragmentActivity::class.java)

    lateinit var presenter: MostRatedPresenter

    private val screen = MostRatedScreen()


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

    inner class MostRatedScreen(){
        private lateinit var fragment: MostRatedFragment

        fun start(){
            activityRule.launchActivity(Intent())
            fragment = MostRatedFragment.newInstance() as MostRatedFragment
            activityRule.activity.setFragment(fragment)
            waitForFragmentAttach()

            presenter=fragment.presenter as MostRatedPresenter
        }

        fun showProgressBar() {
            runOnUIThread {
                fragment.showProgressBar()
            }

            Espresso.onView(ViewMatchers.withId(R.id.progressBar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            Espresso.onView(ViewMatchers.withId(R.id.recyclerView)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
        }

        fun hideProgressBar() {
            runOnUIThread {
                fragment.hideProgressBar()
            }

            Espresso.onView(ViewMatchers.withId(R.id.progressBar)).check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
            Espresso.onView(ViewMatchers.withId(R.id.recyclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

        fun showErrorDialog() {
            runOnUIThread {
                fragment.showErrorDialog(ERROR_MESSAGE)
            }

            Espresso.onView(ViewMatchers.withText(R.string.error)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

        fun hideDialog() {
            runOnUIThread {
                fragment.hideErrorDialog()
            }

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
        private const val ERROR_MESSAGE = "Error"
    }

}
