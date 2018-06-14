package com.example.eastagile.themoviedb

import com.example.eastagile.themoviedb.di.AppTestInjector

class TestMovieDBApplication: MovieDBApplication(){

    override fun injectDependencies() {
        AppTestInjector.init(this)
    }

}