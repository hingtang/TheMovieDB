package com.example.eastagile.themoviedb.di

import com.example.eastagile.themoviedb.presentation.detail.DetailActivity
import com.example.eastagile.themoviedb.presentation.detail.detailTab.DetailFragment
import com.example.eastagile.themoviedb.presentation.detail.reviewTab.ReviewFragment
import com.example.eastagile.themoviedb.presentation.home.HomeActivity
import com.example.eastagile.themoviedb.presentation.home.base.BaseListFragment
import com.example.eastagile.themoviedb.presentation.home.mostRated.MostRatedFragment
import com.example.eastagile.themoviedb.presentation.home.popular.PopularFragment
import com.example.eastagile.themoviedb.test.TestFragmentActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule{

    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector
    abstract fun contributeDetailActivity(): DetailActivity

    @ContributesAndroidInjector
    abstract fun contributeBaseListFragment(): BaseListFragment

    @ContributesAndroidInjector
    abstract fun contributeTestFragmentActivity(): TestFragmentActivity

    @ContributesAndroidInjector
    abstract fun contributePopularFragment(): PopularFragment

    @ContributesAndroidInjector
    abstract fun contributeMostRatedFragment(): MostRatedFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment

    @ContributesAndroidInjector
    abstract fun contributeReviewFragment(): ReviewFragment

}