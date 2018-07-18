package com.example.eastagile.themoviedb.data

import com.example.eastagile.themoviedb.MovieDBApplication
import io.reactivex.Observable

interface MoviesGateway {

    fun getFavouriteMovieList(): Observable<ArrayList<Movie>>

    fun addFavourite(movie: Movie): Observable<Boolean>

    fun isFavoured(id: Int): Observable<Boolean>

    fun removeFavouriteMovie(id: Int): Observable<Boolean>


}

class MoviesDBGateway : MoviesGateway {

    override fun getFavouriteMovieList(): Observable<ArrayList<Movie>> {
        return Observable.create { emitter ->
            val result = MovieDBApplication.database!!.moviesGatewayDao().getFavouriteMovie()
            emitter.onNext(ArrayList(result))
            emitter.onComplete()
        }
    }

    override fun addFavourite(movie: Movie): Observable<Boolean> {
        return Observable.create { emitter ->
            MovieDBApplication.database!!.moviesGatewayDao().insertFavouriteMovie(movie)
            emitter.onNext(MovieDBApplication.database!!.moviesGatewayDao().isMovieExistWithMovieId(movie.id))
            emitter.onComplete()
        }
    }

    override fun isFavoured(id: Int): Observable<Boolean> {
        return Observable.create { emitter ->
            emitter.onNext(MovieDBApplication.database!!.moviesGatewayDao().isMovieExistWithMovieId(id))
            emitter.onComplete()
        }
    }

    override fun removeFavouriteMovie(id: Int): Observable<Boolean> {
        return Observable.create { emitter ->
            val result = MovieDBApplication.database!!.moviesGatewayDao().removeFavouriteMovie(id)
            emitter.onNext(result > -1)
            emitter.onComplete()
        }
    }
}
