package com.example.eastagile.themoviedb.presentation.home.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.eastagile.themoviedb.R
import com.example.eastagile.themoviedb.data.Movie
import com.example.eastagile.themoviedb.utils.AppConstant
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter(private var movieList: ArrayList<Movie>,
                       private val context: Context)
    : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int = movieList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(context, movieList[position])

    fun setMovieList(movies: ArrayList<Movie>){
        this.movieList = movies
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMovieName = view.tvMovieName!!
        val imgvMovie = view.imgvMovie!!
        val imgbtnFavourite = view.imgbtnFavourite!!

        fun bind(context: Context, item: Movie){
            tvMovieName.text = item.title
            val posterUrl = AppConstant.POSTER_URL+item.posterPath
            Glide.with(context).load(posterUrl).apply(RequestOptions.centerCropTransform()).into(imgvMovie)
        }
    }
}