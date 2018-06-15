package com.example.eastagile.themoviedb.presentation.home.base

import android.support.test.annotation.UiThreadTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.eastagile.themoviedb.R
import com.example.eastagile.themoviedb.data.Movie
import com.example.eastagile.themoviedb.test.TestFragmentActivity
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieListAdapterTest{

    private lateinit var adapter: MovieListAdapter
    private lateinit var recyclerView: RecyclerView

    @get:Rule
    val activityRule = ActivityTestRule<TestFragmentActivity>(TestFragmentActivity::class.java)

    @Before
    @UiThreadTest
    fun setUp(){
        adapter = MovieListAdapter(ArrayList(), ArrayList(), activityRule.activity)
        recyclerView = RecyclerView(activityRule.activity)
        recyclerView.id = R.id.recyclerView
        activityRule.activity.setContentView(recyclerView)
        recyclerView.layoutManager = GridLayoutManager(activityRule.activity, 2)
        recyclerView.adapter=adapter
    }

    @Test
    fun should_notify_data_set_change_when_set_movie_list() {
        adapter.setMovieList(mockMoviesData())

        assertTrue(adapter.itemCount == mockMoviesData().size)
    }

    private fun mockMoviesData(): ArrayList<Movie> {
        val movieList = ArrayList<Movie>()
        for (i in 1..10) {
            movieList.add(Movie(i, title = "Movie $i"))
        }
        return movieList
    }
}
