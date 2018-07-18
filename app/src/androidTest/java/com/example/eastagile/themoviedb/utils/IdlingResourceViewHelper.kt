package com.example.eastagile.themoviedb.utils

import android.support.test.espresso.*
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.view.View
import org.hamcrest.Matcher

class IdlingResourceViewHelper private constructor(private val resId: Int) {

    fun on(view: View): ViewIdlingResource {
        val idlingResource = ViewIdlingResource(view)

        onView(withId(resId)).perform(object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isAssignableFrom(View::class.java)
            }

            override fun getDescription(): String {
                return "Registering Idling Resources"
            }

            override fun perform(uiController: UiController, view: View) {
                IdlingRegistry.getInstance().register(idlingResource)
                try {
                    uiController.loopMainThreadUntilIdle()
                } finally {
                    IdlingRegistry.getInstance().unregister(idlingResource)
                }
            }
        })

        return idlingResource
    }

    companion object {

        fun waitViewDisplayed(resId: Int): IdlingResourceViewHelper {
            return IdlingResourceViewHelper(resId)
        }
    }
}
