package com.example.eastagile.themoviedb.presentation.home

import android.support.test.annotation.UiThreadTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v4.view.ViewPager
import com.example.eastagile.themoviedb.R
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomePagerAdapterTest{

    private lateinit var adapter: HomePagerAdapter
    private lateinit var viewPager: ViewPager

    @get:Rule
    val activityRule = ActivityTestRule<HomeActivity>(HomeActivity::class.java)

    @Before
    @UiThreadTest
    fun setUp(){
        adapter = HomePagerAdapter(activityRule.activity.supportFragmentManager)
        viewPager = ViewPager(activityRule.activity)
        viewPager.id = R.id.viewPager
        activityRule.activity.setContentView(viewPager)
        viewPager.adapter=adapter
    }

    @Test
    fun should_return_3_in_item_count() {
        assertTrue(adapter.count==3)
    }
}