package com.example.eastagile.themoviedb.server

import com.example.eastagile.themoviedb.server.reponses.GetMovieListReponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RequestInterface{

    @GET("discover/movie")
    fun getPopularMovies(@Query("api_key") apiKey: String): Observable<GetMovieListReponse>

}