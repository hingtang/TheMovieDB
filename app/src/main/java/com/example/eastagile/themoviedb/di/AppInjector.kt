package com.example.eastagile.themoviedb.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.example.eastagile.themoviedb.MovieDBApplication
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

class AppInjector private constructor() {

    companion object {
        fun init(application: MovieDBApplication) {
            DaggerAppComponent.builder().application(application)
                    .build().inject(application)

            application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                override fun onActivityPaused(activity: Activity?) {
                }

                override fun onActivityResumed(activity: Activity?) {
                }

                override fun onActivityStarted(activity: Activity?) {
                }

                override fun onActivityDestroyed(activity: Activity?) {
                }

                override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
                }

                override fun onActivityStopped(activity: Activity?) {
                }

                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    handleActivity(activity)
                }
            })
        }

        private fun handleActivity(activity: Activity) {
            if(activity is Injectable){
                AndroidInjection.inject(activity)
            }

            if (activity !is FragmentActivity || activity !is HasSupportFragmentInjector) return

            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(object
                : FragmentManager.FragmentLifecycleCallbacks() {

                override fun onFragmentCreated(fragmentManager: FragmentManager?,
                                               fragment: Fragment?,
                                               savedInstanceState: Bundle?) {
                    if(fragment is Injectable) AndroidSupportInjection.inject(fragment)
                }
            }, true)

        }
    }

}
