package com.example.eastagile.themoviedb.di

import android.app.Application
import com.example.eastagile.themoviedb.TestMovieDBApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(MockNetworkModule::class), (MockAppModule::class), (ActivityModule::class), (AndroidSupportInjectionModule::class)])
interface AppTestComponent{

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder
        fun build() : AppTestComponent
    }

    fun inject(application: TestMovieDBApplication)

}