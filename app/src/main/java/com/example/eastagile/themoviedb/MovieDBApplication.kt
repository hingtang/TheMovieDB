package com.example.eastagile.themoviedb

import android.app.Activity
import android.app.Application
import com.example.eastagile.themoviedb.di.AppInjector
import com.example.eastagile.themoviedb.server.RequestInterface
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

open class MovieDBApplication: Application(), HasActivityInjector{

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        injectDependencies()
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    open fun injectDependencies(){
        AppInjector.init(this)
    }
}