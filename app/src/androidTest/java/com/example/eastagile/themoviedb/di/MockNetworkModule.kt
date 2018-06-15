package com.example.eastagile.themoviedb.di

import com.example.eastagile.themoviedb.server.RequestInterface
import com.example.eastagile.themoviedb.utils.AppConstant
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MockNetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(AppConstant.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideRequestInterface(retrofit: Retrofit): RequestInterface = retrofit.create(RequestInterface::class.java)

}

