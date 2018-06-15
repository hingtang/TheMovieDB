package com.example.eastagile.themoviedb.presentation.detail.detailTab

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.eastagile.themoviedb.R
import com.example.eastagile.themoviedb.data.Trailer
import com.example.eastagile.themoviedb.utils.AppConstant
import kotlinx.android.synthetic.main.item_trailer.view.*


class DetailTrailerListAdapter(private var trailerList: ArrayList<Trailer>,
                               private val context: Context)
    : RecyclerView.Adapter<DetailTrailerListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailTrailerListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_trailer, parent, false))
    }

    override fun getItemCount(): Int = trailerList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(context, trailerList[position])

    fun setTrailerList(trailers: ArrayList<Trailer>) {
        this.trailerList = trailers
        notifyDataSetChanged()
    }

    fun getTrailer(position: Int): Trailer {
        return trailerList[position]
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTrailerName = view.tvTrailerName
        private val imgvTrailer = view.imgvTrailder

        fun bind(context: Context, item: Trailer) {
            tvTrailerName.text = item.name
            val trailerUrl = AppConstant.YOUTUBE_THUMBNAILS + item.key + "/0.jpg"
            Glide.with(context).load(trailerUrl).apply(RequestOptions.centerCropTransform()).into(imgvTrailer)
        }
    }
}