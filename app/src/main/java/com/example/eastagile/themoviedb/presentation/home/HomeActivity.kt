package com.example.eastagile.themoviedb.presentation.home

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.Menu
import com.example.eastagile.themoviedb.R
import com.example.eastagile.themoviedb.data.Movie
import com.example.eastagile.themoviedb.di.Injectable
import com.example.eastagile.themoviedb.presentation.detail.detailTab.DetailContract
import com.example.eastagile.themoviedb.presentation.home.base.BaseListFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject
import javax.inject.Named

class HomeActivity : AppCompatActivity(), HasSupportFragmentInjector, Injectable, HomeContract.View {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    @VisibleForTesting
    @field:Named("HomePresenter")
    lateinit var presenter: HomeContract.Presenter

    var favouriteList: ArrayList<Movie> = ArrayList()
    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter.attachView(this)
        initToolbar()
        initTab()
    }

    override fun onStart() {
        super.onStart()
        presenter.getFavouriteMovieList()
    }

    private fun initTab() {
        val fragmentPagerAdapter = HomePagerAdapter(supportFragmentManager)
        viewPager.adapter = fragmentPagerAdapter

        tabLayout.setupWithViewPager(viewPager)

        tabLayout.getTabAt(0)!!.setText(R.string.tab_popular)
        tabLayout.getTabAt(1)!!.setText(R.string.tab_most_rated)
        tabLayout.getTabAt(2)!!.setText(R.string.tab_my_favourite)
    }

    private fun initToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun showErrorDialog(message: String) {
        if (dialog == null) {
            val builder = AlertDialog.Builder(this)
                    .setTitle(R.string.error)
                    .setMessage(message)
                    .setPositiveButton(R.string.ok, null)
            dialog = builder.create()
        }

        dialog!!.show()
    }

    override fun hideErrorDialog() {
        dialog?.dismiss()
    }

    override fun setFavouriteMovieList(movieList: ArrayList<Movie>) {
        this.favouriteList = movieList
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
}
