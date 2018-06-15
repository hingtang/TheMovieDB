package com.example.eastagile.themoviedb.presentation.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import com.example.eastagile.themoviedb.R
import com.example.eastagile.themoviedb.data.Movie
import com.example.eastagile.themoviedb.di.Injectable
import com.example.eastagile.themoviedb.presentation.detail.detailTab.DetailPresenter
import com.google.gson.Gson
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), HasSupportFragmentInjector, Injectable {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        initToolbar()
        initTab()
    }

    private fun initTab() {
        val intent = intent
        if (intent != null) {
            val movieJson = intent.getStringExtra("MOVIE")
            val movie = Gson().fromJson<Movie>(movieJson, Movie::class.java)
            val fragmentPagerAdapter = DetailPagerAdapter.newInstance(supportFragmentManager, movie)
            viewPager.adapter = fragmentPagerAdapter

            tabLayout.setupWithViewPager(viewPager)

            tabLayout.getTabAt(0)!!.setText(R.string.tab_details)
            tabLayout.getTabAt(1)!!.setText(R.string.tab_reviews)
        }
    }

    private fun initToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
}
