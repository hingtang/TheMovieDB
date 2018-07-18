package com.example.eastagile.themoviedb.presentation.home.mostRated

import com.example.eastagile.themoviedb.data.MoviesGateway
import com.example.eastagile.themoviedb.presentation.home.base.BaseListContract
import com.example.eastagile.themoviedb.server.RequestInterface
import com.example.eastagile.themoviedb.utils.AppConstant
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MostRatedPresenter(private val requestInterface: RequestInterface,
                         private val ioScheduler: Scheduler = Schedulers.io(),
                         private val uiScheduler: Scheduler = AndroidSchedulers.mainThread())
    : BaseListContract.Presenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    lateinit var view: BaseListContract.View

    override fun getMovieList() {
        compositeDisposable.add(requestInterface.getMostRatedMovies()
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe({ response ->
                    view.setMovieList(response.results)
                }, { error ->
                    view.hideProgressBar()
                    view.showErrorDialog(error.message ?: "")
                    error.printStackTrace()
                }, {
                    view.hideProgressBar()
                }))
    }

    override fun attachView(view: BaseListContract.View) {
        this.view = view
    }

    override fun detachView() {
        compositeDisposable.dispose()
    }
}
