package com.example.eastagile.themoviedb.server.reponses

import com.example.eastagile.themoviedb.data.Trailer

data class GetTrailerListResponse(

        val id: Int,

        val results: ArrayList<Trailer>

)