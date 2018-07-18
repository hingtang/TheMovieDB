package com.example.eastagile.themoviedb.presentation.detail.detailTab

import com.example.eastagile.themoviedb.base.BasePresenter
import com.example.eastagile.themoviedb.data.Movie
import com.example.eastagile.themoviedb.data.Trailer

interface DetailContract{

    interface View{

        fun setTrailerList(trailerList: ArrayList<Trailer>)

        fun showProgressBar()

        fun hideProgressBar()

        fun showErrorDialog(message: String)

        fun hideErrorDialog()

        fun playVideo(position: Int)

        fun setFavourite(isFavoured: Boolean)

    }

    interface Presenter : BasePresenter<View> {

        fun getTrailerList(movieId: Int)

        fun addFavourite(movie: Movie)

        fun deleteFavourite(movieId: Int)

        fun setFavourite(movieId: Int)

        fun clickFavourite(movie: Movie)

    }

}

