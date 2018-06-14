package com.example.eastagile.themoviedb.presentation.home.base

import com.example.eastagile.themoviedb.base.BasePresenter
import com.example.eastagile.themoviedb.data.Movie

interface BaseListContract{

    interface View {

        fun setMovieList(movieList: ArrayList<Movie>)

        fun showProgressBar()

        fun hideProgressBar()

        fun showErrorDialog()

        fun hideErrorDialog()

        fun navigateToMovieDetail(movieId: Int)

    }

    interface Presenter : BasePresenter<View> {

        fun getMovieList()

    }

}