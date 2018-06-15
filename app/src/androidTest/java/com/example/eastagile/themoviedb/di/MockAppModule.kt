package com.example.eastagile.themoviedb.di

import com.example.eastagile.themoviedb.data.MoviesDBGateway
import com.example.eastagile.themoviedb.data.MoviesGateway
import com.example.eastagile.themoviedb.presentation.detail.detailTab.DetailContract
import com.example.eastagile.themoviedb.presentation.detail.detailTab.DetailPresenter
import com.example.eastagile.themoviedb.presentation.detail.reviewTab.ReviewContract
import com.example.eastagile.themoviedb.presentation.detail.reviewTab.ReviewPresenter
import com.example.eastagile.themoviedb.presentation.home.HomeContract
import com.example.eastagile.themoviedb.presentation.home.HomePresenter
import com.example.eastagile.themoviedb.presentation.home.base.BaseListContract
import com.example.eastagile.themoviedb.presentation.home.mostrated.MostRatedPresenter
import com.example.eastagile.themoviedb.presentation.home.popular.PopularPresenter
import com.example.eastagile.themoviedb.server.RequestInterface
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class MockAppModule{

    @Singleton
    @Provides
    fun provideServerMoviesGateway(): MoviesGateway = MoviesDBGateway()

    @Singleton
    @Provides
    @Named("PopularPresenter")
    fun providePopularPresenter(requestInterface: RequestInterface)
            : BaseListContract.Presenter = PopularPresenter(requestInterface)


    @Singleton
    @Provides
    @Named("MostRatedPresenter")
    fun provideMostRatedPresenter(requestInterface: RequestInterface)
            : BaseListContract.Presenter = MostRatedPresenter(requestInterface)

    @Singleton
    @Provides
    @Named("DetailPresenter")
    fun provideDetailPresenter(requestInterface: RequestInterface, moviesGateway: MoviesGateway)
            : DetailContract.Presenter = DetailPresenter(requestInterface, moviesGateway)

    @Singleton
    @Provides
    @Named("HomePresenter")
    fun provideHomePresenter(moviesGateway: MoviesGateway)
            : HomeContract.Presenter = HomePresenter(moviesGateway)

    @Singleton
    @Provides
    @Named("ReviewPresenter")
    fun provideReviewPresenter(requestInterface: RequestInterface)
            : ReviewContract.Presenter = ReviewPresenter(requestInterface)

}
