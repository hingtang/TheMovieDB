package com.example.eastagile.themoviedb.di

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import com.example.eastagile.themoviedb.TestMovieDBApplication
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector

class AppTestInjector private constructor(){

    companion object {
        fun init(application: TestMovieDBApplication){
            DaggerAppTestComponent.builder()
                    .application(application)
                    .build()
                    .inject(application)
            application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, saveInstanceState: Bundle?) {
                    handleActivity(activity)
                }

                override fun onActivityStarted(p0: Activity?) {}

                override fun onActivityResumed(p0: Activity?) {}

                override fun onActivityPaused(p0: Activity?) {}

                override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {}

                override fun onActivityStopped(p0: Activity?) {}

                override fun onActivityDestroyed(p0: Activity?) {}
            })
        }



        private fun handleActivity(activity: Activity){
            if (activity is Injectable) {
                AndroidInjection.inject(activity)
            }

            if (activity !is FragmentActivity || activity !is HasSupportFragmentInjector) return

            activity.supportFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentCreated(fragmentManager: FragmentManager?, fragment: Fragment?, savedInstanceState: Bundle?) {
                    if (fragment is Injectable) {
                        AndroidSupportInjection.inject(fragment)
                    }
                }
            }, true)
        }
    }
}