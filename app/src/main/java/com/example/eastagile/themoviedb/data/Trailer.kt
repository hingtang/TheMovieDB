package com.example.eastagile.themoviedb.data

import com.google.gson.annotations.SerializedName

data class Trailer(

        val id: String,

        @SerializedName("ios_639_1")
        val ios639: String,

        @SerializedName("ios_3166_1")
        val ios3166: String,

        val key: String,

        val name: String,

        val site: String,

        val size: Int,

        val type: String
)
