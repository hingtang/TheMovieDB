package com.example.eastagile.themoviedb.presentation.detail.reviewTab

import com.example.eastagile.themoviedb.server.RequestInterface
import com.example.eastagile.themoviedb.server.reponses.GetReviewListResponse
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Assert.assertNotNull
import org.junit.Test

class ReviewPresenterTest{

    private val view = mock<ReviewContract.View>()

    private val requestInterface = mock<RequestInterface>()

    private val presenter = ReviewPresenter(requestInterface, Schedulers.trampoline(),
            Schedulers.trampoline())

    @Test
    fun `view should be assigned after attached`() {
        presenter.attachView(view)

        try {
            presenter.view.showProgressBar()
        } catch (exception: UninitializedPropertyAccessException) {
            Assert.fail()
        }
    }

    @Test
    fun `should return a review list when get movie review`() {
        whenever(requestInterface.getReview(MOVIE_ID))
                .thenReturn(Observable.just(GetReviewListResponse(1,
                        results = ArrayList())))

        val response = requestInterface.getReview(MOVIE_ID)
        val testObservable = TestObserver.create<GetReviewListResponse>()
        response.subscribe(testObservable)
        testObservable.assertComplete()
        testObservable.assertSubscribed()

        val reviewListResponse = testObservable.values()[0]
        assertNotNull(reviewListResponse)
    }

    companion object {
        private const val MOVIE_ID = 1
    }
}