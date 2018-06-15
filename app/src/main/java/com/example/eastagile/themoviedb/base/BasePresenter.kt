package com.example.eastagile.themoviedb.base

interface BasePresenter<in T>{

    fun attachView(view: T)

    fun detachView()

}
