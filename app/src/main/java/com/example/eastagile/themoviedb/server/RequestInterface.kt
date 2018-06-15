package com.example.eastagile.themoviedb.server

import com.example.eastagile.themoviedb.server.reponses.GetMovieListReponse
import com.example.eastagile.themoviedb.server.reponses.GetTrailerListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestInterface {

    @GET("discover/movie?api_key=4b311fea5f847a0dc856b15f182a0455")
    fun getPopularMovies(): Observable<GetMovieListReponse>

    @GET("movie/top_rated?api_key=4b311fea5f847a0dc856b15f182a0455")
    fun getMostRatedMovies(): Observable<GetMovieListReponse>

    @GET("movie/{id}/videos?api_key=4b311fea5f847a0dc856b15f182a0455")
    fun getTrailers(@Path("id") id: Int): Observable<GetTrailerListResponse>

}
