package com.example.eastagile.themoviedb.server.reponses

import com.example.eastagile.themoviedb.data.Review

data class GetReviewListResponse (

        val id: Int,

        val page: Int=1,

        val results: ArrayList<Review>,

        val total_pages: Int=1,

        val total_results:Int=1

)
