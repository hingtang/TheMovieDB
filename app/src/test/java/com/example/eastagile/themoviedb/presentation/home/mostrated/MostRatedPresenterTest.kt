package com.example.eastagile.themoviedb.presentation.home.mostrated

import com.example.eastagile.themoviedb.presentation.home.base.BaseListContract
import com.example.eastagile.themoviedb.server.RequestInterface
import com.example.eastagile.themoviedb.server.reponses.GetMovieListResponse
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Test

class MostRatedPresenterTest{

    private val view = mock<BaseListContract.View>()

    private val requestInterface = mock<RequestInterface>()

    private val presenter = MostRatedPresenter(requestInterface, Schedulers.trampoline(),
            Schedulers.trampoline())

    @Test
    fun `view should be assigned after attached`() {
        presenter.attachView(view)

        try {
            presenter.view.navigateToMovieDetail(0)
        } catch (exception: UninitializedPropertyAccessException) {
            Assert.fail()
        }
    }

    @Test
    fun `should return a movie list when get top rated movie`() {
        whenever(requestInterface.getMostRatedMovies())
                .thenReturn(Observable.just(GetMovieListResponse(1,
                        10,
                        1,
                        ArrayList())))

        val response = requestInterface.getMostRatedMovies()
        val testObservable = TestObserver.create<GetMovieListResponse>()
        response.subscribe(testObservable)
        testObservable.assertComplete()
        testObservable.assertSubscribed()

        val movieList = testObservable.values()[0]
        Assert.assertNotNull(movieList)
    }

}
