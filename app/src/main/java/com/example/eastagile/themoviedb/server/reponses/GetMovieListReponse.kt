package com.example.eastagile.themoviedb.server.reponses

import android.arch.persistence.room.ColumnInfo
import com.example.eastagile.themoviedb.data.Movie

data class GetMovieListReponse (

        val page: Int,

        val totalResults: Int,

        val totalPages: Int,

        val results: ArrayList<Movie>
)