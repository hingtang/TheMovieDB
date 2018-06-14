package com.example.eastagile.themoviedb.presentation.home.mostRated

import com.example.eastagile.themoviedb.data.Movie
import com.example.eastagile.themoviedb.presentation.home.base.BaseListContract

class MostRatedPresenter : BaseListContract.Presenter {

    lateinit var view: BaseListContract.View

//    override fun getMovieList(): ArrayList<String> {
//        view.showProgressBar()
//        Thread.sleep(2000)
//        view.hideProgressBar()
//        return mockMovie()
//    }

    override fun getMovieList() {

    }

    override fun attachView(view: BaseListContract.View) {
        this.view = view
    }

    override fun detachView() {
    }

    private fun mockMovie(): ArrayList<String> {
        val movieList = ArrayList<String>()
        for (i in 1 .. 10){
            movieList += "Most Rated Movie $i"
        }
        return movieList
    }
}