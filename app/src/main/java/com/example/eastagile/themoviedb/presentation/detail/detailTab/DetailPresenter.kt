package com.example.eastagile.themoviedb.presentation.detail.detailTab

import com.example.eastagile.themoviedb.data.Movie
import com.example.eastagile.themoviedb.data.MoviesGateway
import com.example.eastagile.themoviedb.data.Trailer
import com.example.eastagile.themoviedb.server.RequestInterface
import com.example.eastagile.themoviedb.utils.AppConstant
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailPresenter(private val requestInterface: RequestInterface,
                      private val moviesGateway: MoviesGateway,
                      private val ioScheduler: Scheduler = Schedulers.io(),
                      private val uiScheduler: Scheduler = AndroidSchedulers.mainThread())
    : DetailContract.Presenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    lateinit var view: DetailContract.View

    override fun getTrailerList(movieId: Int) {
        compositeDisposable.add(requestInterface.getTrailers(movieId)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe({ response ->
                    view.hideErrorDialog()
                    view.setTrailerList(response.results)
                }, { error ->
                    view.hideProgressBar()
                    view.showErrorDialog(error.message ?: "")
                }, {
                    view.hideProgressBar()
                }))
    }

    override fun addFavourite(movie: Movie) {
        moviesGateway.addFavourite(movie)
                .subscribeOn(ioScheduler)
                .subscribe({ isSuccess ->
                    if (isSuccess) {
                        view.setFavourite(true)
                    }
                }, { error ->
                    view.showErrorDialog(error.message ?: "")
                })
    }

    override fun deleteFavourite(movieId: Int) {
        moviesGateway.removeFavouriteMovie(movieId)
                .subscribeOn(ioScheduler)
                .subscribe({ isSuccess ->
                    if (isSuccess) {
                        view.setFavourite(false)
                    }
                }, { error ->
                    view.showErrorDialog(error.message ?: "")
                })
    }

    override fun setFavourite(movieId: Int) {
        moviesGateway.isFavoured(movieId).subscribeOn(ioScheduler)
                .subscribe({ isFavourite ->
                    view.setFavourite(isFavourite)
                }, { error ->
                    view.showErrorDialog(error.message ?: "")
                })
    }

    override fun clickFavourite(movie: Movie) {
        moviesGateway.isFavoured(movie.id).subscribeOn(ioScheduler)
                .subscribe({ isFavourite ->
                    if(isFavourite){
                        deleteFavourite(movie.id)
                    }else{
                        addFavourite(movie)
                    }
                }, { error ->
                    view.showErrorDialog(error.message ?: "")
                })
    }

    override fun attachView(view: DetailContract.View) {
        this.view = view
    }

    override fun detachView() {
        compositeDisposable.dispose()
    }
}
