package com.example.eastagile.themoviedb.presentation.detail.reviewTab

import com.example.eastagile.themoviedb.server.RequestInterface
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ReviewPresenter(private val requestInterface: RequestInterface,
                      private val ioScheduler: Scheduler = Schedulers.io(),
                      private val uiScheduler: Scheduler = AndroidSchedulers.mainThread())
    : ReviewContract.Presenter {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    lateinit var view: ReviewContract.View

    override fun getReviewList(movieId: Int) {
        compositeDisposable.add(requestInterface.getReview(movieId)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler)
                .subscribe({ response ->
                    view.hideErrorDialog()
                    view.setReviewList(response.results)
                }, { error ->
                    view.hideProgressBar()
                    view.showErrorDialog(error.message ?: "")
                }, {
                    view.hideProgressBar()
                }))
    }

    override fun attachView(view: ReviewContract.View) {
        this.view = view
    }

    override fun detachView() {
        compositeDisposable.dispose()
    }
}