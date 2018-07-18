package com.example.eastagile.themoviedb.server.reponses

import com.example.eastagile.themoviedb.data.Movie

data class GetMovieListReponse (

        val page: Int,

        val totalResults: Int,

        val totalPages: Int,

        val results: ArrayList<Movie>
)
