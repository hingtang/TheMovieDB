package com.example.eastagile.themoviedb.di

import com.example.eastagile.themoviedb.presentation.home.base.BaseListContract
import com.example.eastagile.themoviedb.presentation.home.mostRated.MostRatedPresenter
import com.example.eastagile.themoviedb.presentation.home.popular.PopularPresenter
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class MockAppModule{

    @Singleton
    @Provides
    @Named("PopularPresenter")
    fun providePopularPresenter(): BaseListContract.Presenter = mock<PopularPresenter>()

    @Singleton
    @Provides
    @Named("MostRatedPresenter")
    fun provideMostRatedPresenter(): BaseListContract.Presenter = mock<MostRatedPresenter>()

}