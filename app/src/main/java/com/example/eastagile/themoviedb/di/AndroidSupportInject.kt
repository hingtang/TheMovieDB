package com.example.eastagile.themoviedb.di

import android.support.v4.app.Fragment
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector

class AndroidSupportInject {

    companion object {

        fun inject(fragment: Fragment) {
            val hasSupportFragmentInjector = findHasFragmentInjector(fragment)

            val fragmentInjector = hasSupportFragmentInjector!!.supportFragmentInjector()

            fragmentInjector.inject(fragment)
        }

        private fun findHasFragmentInjector(fragment: Fragment): HasSupportFragmentInjector? {
            val parentFragment = fragment.parentFragment
            if (parentFragment != null && parentFragment is HasSupportFragmentInjector) {
                return parentFragment
            }

            val activity = fragment.activity
            if (activity != null) {
                if (activity is HasFragmentInjector) {
                    return activity as HasSupportFragmentInjector
                }
                if (activity.application is HasSupportFragmentInjector) {
                    return activity.application as HasSupportFragmentInjector
                }
            }

            return null
        }
    }

}