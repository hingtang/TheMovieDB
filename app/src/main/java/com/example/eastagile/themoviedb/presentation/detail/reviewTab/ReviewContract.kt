package com.example.eastagile.themoviedb.presentation.detail.reviewTab

import com.example.eastagile.themoviedb.base.BasePresenter
import com.example.eastagile.themoviedb.data.Review

interface ReviewContract{

    interface View{

        fun setReviewList(reviewList: ArrayList<Review>)

        fun showProgressBar()

        fun hideProgressBar()

        fun showErrorDialog(message: String)

        fun hideErrorDialog()

    }

    interface Presenter : BasePresenter<View> {

        fun getReviewList(movieId: Int)

    }

}