package com.example.eastagile.themoviedb.server.reponses

import android.arch.persistence.room.ColumnInfo
import com.example.eastagile.themoviedb.data.Movie

data class GetMovieListReponse (

        @ColumnInfo(name = "page")
        val page: Int,

        @ColumnInfo(name = "total_results")
        val totalResults: Int,

        @ColumnInfo(name = "total_pages")
        val totalPages: Int,

        @ColumnInfo(name = "results")
        val results: ArrayList<Movie>
)