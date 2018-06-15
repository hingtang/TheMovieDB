package com.example.eastagile.themoviedb.presentation.home

import com.example.eastagile.themoviedb.data.MoviesGateway
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenter(private val moviesGateway: MoviesGateway,
                    private val ioScheduler: Scheduler = Schedulers.io(),
                    private val uiScheduler: Scheduler = AndroidSchedulers.mainThread())
    : HomeContract.Presenter{

    lateinit var view: HomeContract.View

    override fun getFavouriteMovieList() {
        moviesGateway.getFavouriteMovieList()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe({ movieList ->
                    view.hideErrorDialog()
                    view.setFavouriteMovieList(movieList)
                }, { error ->
                    view.showErrorDialog(error.message ?: "")
                })
    }

    override fun attachView(view: HomeContract.View) {
        this.view = view
    }

    override fun detachView() {

    }
}