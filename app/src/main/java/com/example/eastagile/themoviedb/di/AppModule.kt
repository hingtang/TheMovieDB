package com.example.eastagile.themoviedb.di

import com.example.eastagile.themoviedb.data.LocalMoviesGateway
import com.example.eastagile.themoviedb.data.MoviesGateway
import com.example.eastagile.themoviedb.presentation.home.base.BaseListContract
import com.example.eastagile.themoviedb.presentation.home.mostRated.MostRatedPresenter
import com.example.eastagile.themoviedb.presentation.home.popular.PopularPresenter
import com.example.eastagile.themoviedb.server.RequestInterface
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideServerMoviesGateway(): MoviesGateway = LocalMoviesGateway()

    @Singleton
    @Provides
    @Named("PopularPresenter")
    fun providePopularPresenter(requestInterface: RequestInterface): BaseListContract.Presenter = PopularPresenter(requestInterface)

    @Singleton
    @Provides
    @Named("MostRatedPresenter")
    fun provideMostRatedPresenter(): BaseListContract.Presenter = MostRatedPresenter()

}