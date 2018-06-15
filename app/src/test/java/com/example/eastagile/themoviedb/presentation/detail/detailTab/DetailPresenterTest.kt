package com.example.eastagile.themoviedb.presentation.detail.detailTab

import com.example.eastagile.themoviedb.data.Movie
import com.example.eastagile.themoviedb.data.MoviesGateway
import com.example.eastagile.themoviedb.server.RequestInterface
import com.example.eastagile.themoviedb.server.reponses.GetTrailerListResponse
import com.example.eastagile.themoviedb.utils.AppConstant
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Assert
import org.junit.Assert.assertNotNull
import org.junit.Test

class DetailPresenterTest{

    private val view = mock<DetailContract.View>()

    private val moviesGateway = mock<MoviesGateway>()

    private val requestInterface = mock<RequestInterface>()

    private val presenter = DetailPresenter(requestInterface, moviesGateway, Schedulers.trampoline(),
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
    fun `should return a trailer list when get popular movie`() {
        whenever(requestInterface.getTrailers(MOVIE_ID, AppConstant.API_KEY))
                .thenReturn(Observable.just(GetTrailerListResponse(1,
                        ArrayList())))

        val response = requestInterface.getTrailers(MOVIE_ID, AppConstant.API_KEY)
        val testObservable = TestObserver.create<GetTrailerListResponse>()
        response.subscribe(testObservable)
        testObservable.assertComplete()
        testObservable.assertSubscribed()

        val trailerListResponse = testObservable.values()[0]
        assertNotNull(trailerListResponse)
    }

    @Test
    fun `should return true when user add favourite success and user didn't favour before`() {
        whenever(moviesGateway.addFavourite(Movie(MOVIE_ID, title = MOVIE_TITLE)))
                .thenReturn(Observable.just(true))

        val result = moviesGateway.addFavourite(Movie(MOVIE_ID, title = MOVIE_TITLE))
        val testObservable = TestObserver.create<Boolean>()
        result.subscribe(testObservable)
        testObservable.assertComplete()
        testObservable.assertNoErrors()
        testObservable.assertSubscribed()

        val isAddFavouriteSuccess = testObservable.values()[0]
        assertTrue(isAddFavouriteSuccess)
    }

    @Test
    fun `should return false when user add favourite failed and user didn't favour before`() {
        whenever(moviesGateway.addFavourite(Movie(MOVIE_ID, title = MOVIE_TITLE)))
                .thenReturn(Observable.just(false))

        val result = moviesGateway.addFavourite(Movie(MOVIE_ID, title = MOVIE_TITLE))
        val testObservable = TestObserver.create<Boolean>()
        result.subscribe(testObservable)
        testObservable.assertComplete()
        testObservable.assertNoErrors()
        testObservable.assertSubscribed()

        val isAddFavouriteSuccess = testObservable.values()[0]
        assertFalse(isAddFavouriteSuccess)
    }

    @Test
    fun `should return true when user remove favourite success and user favoured before`() {
        whenever(moviesGateway.removeFavouriteMovie(MOVIE_ID)).thenReturn(Observable.just(true))

        val result = moviesGateway.removeFavouriteMovie(MOVIE_ID)
        val testObservable = TestObserver.create<Boolean>()
        result.subscribe(testObservable)
        testObservable.assertComplete()
        testObservable.assertNoErrors()
        testObservable.assertSubscribed()

        val isRemoveFavouriteSuccess = testObservable.values()[0]
        assertTrue(isRemoveFavouriteSuccess)
    }

    @Test
    fun `should return false when user remove favourite failed and user favoured before`() {
        whenever(moviesGateway.removeFavouriteMovie(MOVIE_ID)).thenReturn(Observable.just(false))

        val result = moviesGateway.removeFavouriteMovie(MOVIE_ID)
        val testObservable = TestObserver.create<Boolean>()
        result.subscribe(testObservable)
        testObservable.assertComplete()
        testObservable.assertNoErrors()
        testObservable.assertSubscribed()

        val isRemoveFavouriteSuccess = testObservable.values()[0]
        assertFalse(isRemoveFavouriteSuccess)
    }

    @Test
    fun `should return true when movie is favoured`() {
        whenever(moviesGateway.isFavoured(MOVIE_ID)).thenReturn(Observable.just(true))

        val result = moviesGateway.isFavoured(MOVIE_ID)
        val testObservable = TestObserver.create<Boolean>()
        result.subscribe(testObservable)
        testObservable.assertComplete()
        testObservable.assertNoErrors()
        testObservable.assertSubscribed()

        val isFavoured = testObservable.values()[0]
        assertTrue(isFavoured)
    }

    @Test
    fun `should return false when movie is non-favourite`() {
        whenever(moviesGateway.isFavoured(MOVIE_ID)).thenReturn(Observable.just(false))

        val result = moviesGateway.isFavoured(MOVIE_ID)
        val testObservable = TestObserver.create<Boolean>()
        result.subscribe(testObservable)
        testObservable.assertComplete()
        testObservable.assertNoErrors()
        testObservable.assertSubscribed()

        val isFavoured = testObservable.values()[0]
        assertFalse(isFavoured)
    }

    companion object {
        private const val MOVIE_ID = 1
        private const val MOVIE_TITLE= "Title"
    }
}
