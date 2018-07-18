package com.example.eastagile.themoviedb.presentation.home.base

import android.content.Context
import android.content.res.ColorStateList
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.eastagile.themoviedb.R
import com.example.eastagile.themoviedb.data.Movie
import com.example.eastagile.themoviedb.utils.AppConstant
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter(private var movieList: ArrayList<Movie>,
                       private var favouriteList: ArrayList<Movie>,
                       private val context: Context)
    : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int = movieList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(context, favouriteList, movieList[position])

    fun setMovieList(movies: ArrayList<Movie>) {
        this.movieList = movies
        notifyDataSetChanged()
    }

    fun getMovieList(): ArrayList<Movie> = movieList

    fun setFavouriteList(movies: ArrayList<Movie>) {
        this.favouriteList = movies
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvMovieName = view.tvMovieName
        val imgvMovie = view.imgvMovie
        val fab = view.fab

        fun bind(context: Context, favouriteList: ArrayList<Movie>, item: Movie) {
            tvMovieName.text = item.title
            val posterUrl = AppConstant.POSTER_URL + item.posterPath
            Glide.with(context).load(posterUrl).apply(RequestOptions.centerCropTransform()).into(imgvMovie)
            if (favouriteList.contains(item)) {
                fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favourite))
                fab.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.colorAccent))
            } else {
                fab.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_not_favourite))
                fab.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, android.R.color.white))
            }
        }
    }
}
