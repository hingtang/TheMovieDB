package com.example.eastagile.themoviedb.presentation.home

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import com.example.eastagile.themoviedb.R
import com.example.eastagile.themoviedb.di.Injectable
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HasSupportFragmentInjector, Injectable {

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initToolbar()
        initTab()
    }

    private fun initTab(){
        val fragmentPagerAdapter = HomePagerAdapter(supportFragmentManager)
        viewPager.adapter = fragmentPagerAdapter

        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)!!.setText(R.string.tab_popular)
        tabLayout.getTabAt(1)!!.setText(R.string.tab_most_rated)
        tabLayout.getTabAt(2)!!.setText(R.string.tab_my_favourite)
    }

    private fun initToolbar(){
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
}
