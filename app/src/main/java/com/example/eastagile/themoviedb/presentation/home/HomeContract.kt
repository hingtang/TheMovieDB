package com.example.eastagile.themoviedb.presentation.home

import com.example.eastagile.themoviedb.base.BasePresenter
import com.example.eastagile.themoviedb.data.Movie

interface HomeContract{
    interface View{

        fun showErrorDialog(message: String)

        fun hideErrorDialog()

        fun setFavouriteMovieList(movieList: ArrayList<Movie>)
    }

    interface Presenter: BasePresenter<View> {

        fun getFavouriteMovieList()
    }

}