package com.example.eastagile.themoviedb.presentation.home.popular

import com.example.eastagile.themoviedb.data.Movie
import com.example.eastagile.themoviedb.data.MoviesGateway
import com.example.eastagile.themoviedb.presentation.home.base.BaseListContract
import com.example.eastagile.themoviedb.server.RequestInterface
import com.example.eastagile.themoviedb.server.reponses.GetMovieListReponse
import com.example.eastagile.themoviedb.utils.AppConstant
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class PopularPresenterTest{

    private val view = mock<BaseListContract.View>()

    private val moviesGateway = mock<MoviesGateway>()

    private val requestInterface = mock<RequestInterface>()

    private val presenter = PopularPresenter(requestInterface, Schedulers.computation(),
            Schedulers.computation())

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
    fun `should return a movie list when get popular movie`() {
        whenever(requestInterface.getPopularMovies(AppConstant.API_KEY))
                .thenReturn(Observable.just(GetMovieListReponse(1,
                        10,
                        1,
                        ArrayList())))

        val response = requestInterface.getPopularMovies(AppConstant.API_KEY)
        val testObservable = TestObserver.create<GetMovieListReponse>()
        response.subscribe(testObservable)
        testObservable.assertComplete()
        testObservable.assertSubscribed()

        val movieList = testObservable.values()[0]
        assertNotNull(movieList)
    }
}