package com.example.eastagile.themoviedb.di

import android.app.Application
import com.example.eastagile.themoviedb.MovieDBApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class), (AppModule::class), (ActivityModule::class), (AndroidSupportInjectionModule::class)])
interface AppComponent{

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    fun inject(movieDBApplication: MovieDBApplication)

}