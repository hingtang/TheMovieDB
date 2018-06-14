package com.example.eastagile.themoviedb.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
data class Movie(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: Int,

        @ColumnInfo(name = "vote_count")
        @SerializedName("vote_count")
        val voteCount: Int? = 0,

        @ColumnInfo(name = "video")
        val video: Boolean? = false,

        @ColumnInfo(name = "vote_average")
        @SerializedName("vote_average")
        val voteAverage: Double? = 0.0,

        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "popularity")
        val popularity: Double? = 0.0,

        @ColumnInfo(name = "poster_path")
        @SerializedName("poster_path")
        val posterPath: String? = "",

        @ColumnInfo(name = "original_language")
        @SerializedName("original_language")
        val originalLanguage: String? = "",

        @ColumnInfo(name = "original_title")
        @SerializedName("original_title")
        val originalTitle: String? = "",

        @ColumnInfo(name = "genre_ids")
        @SerializedName("genre_ids")
        val genreIds: ArrayList<Int>? = ArrayList<Int>(),

        @ColumnInfo(name = "backdrop_path")
        @SerializedName("backdrop_path")
        val backdropPath: String? = "",

        @ColumnInfo(name = "adult")
        val adult: Boolean? = false,

        @ColumnInfo(name = "overview")
        val overview: String? = "",

        @ColumnInfo(name = "release_date")
        @SerializedName("release_date")
        val releaseDate: String? = ""
)