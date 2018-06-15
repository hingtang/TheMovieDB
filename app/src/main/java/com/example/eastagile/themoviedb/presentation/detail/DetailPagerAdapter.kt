package com.example.eastagile.themoviedb.presentation.detail

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.eastagile.themoviedb.data.Movie
import com.example.eastagile.themoviedb.presentation.detail.detailTab.DetailFragment
import com.example.eastagile.themoviedb.presentation.detail.reviewTab.ReviewFragment
import com.example.eastagile.themoviedb.presentation.home.popular.PopularFragment

class DetailPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private var movie: Movie? = null

    companion object {
        fun newInstance(fragmentManager: FragmentManager, movie: Movie): DetailPagerAdapter {
            val adapter = DetailPagerAdapter(fragmentManager)
            adapter.movie = movie
            return adapter
        }
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return DetailFragment.newInstance(movie!!)
            1 -> return ReviewFragment.newInstance(movie!!.id)
        }
        return PopularFragment()
    }

    override fun getCount(): Int {
        return 2
    }
}
