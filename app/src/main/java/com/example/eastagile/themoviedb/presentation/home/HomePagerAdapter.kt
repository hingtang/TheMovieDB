package com.example.eastagile.themoviedb.presentation.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.eastagile.themoviedb.presentation.home.mostRated.MostRatedFragment
import com.example.eastagile.themoviedb.presentation.home.myfavourite.MyFavouriteFragment
import com.example.eastagile.themoviedb.presentation.home.popular.PopularFragment

class HomePagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager){

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return PopularFragment()
            1 -> return MostRatedFragment()
            2 -> return MyFavouriteFragment()
        }
        return PopularFragment()
    }

    override fun getCount(): Int {
        return 3
    }
}
