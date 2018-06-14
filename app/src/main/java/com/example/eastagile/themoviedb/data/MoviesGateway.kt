package com.example.eastagile.themoviedb.data

interface MoviesGateway {

    fun getFavouriteMovieList(): ArrayList<Movie>

}

class LocalMoviesGateway : MoviesGateway {

    override fun getFavouriteMovieList(): ArrayList<Movie> {
        return ArrayList()
    }
}