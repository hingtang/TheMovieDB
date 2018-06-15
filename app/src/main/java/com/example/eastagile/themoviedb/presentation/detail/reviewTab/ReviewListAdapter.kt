package com.example.eastagile.themoviedb.presentation.detail.reviewTab

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eastagile.themoviedb.R
import com.example.eastagile.themoviedb.data.Review
import kotlinx.android.synthetic.main.item_review.view.*

class ReviewListAdapter(private var reviewList: ArrayList<Review>,
                               private val context: Context)
    : RecyclerView.Adapter<ReviewListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_review, parent, false))
    }

    override fun getItemCount(): Int = reviewList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(reviewList[position])

    fun setReviewList(reviews: ArrayList<Review>) {
        this.reviewList = reviews
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvAuthor = view.tvAuthor
        private val tvContent = view.tvContent

        fun bind(item: Review) {
            tvAuthor.text = item.author
            tvContent.text=item.content
        }
    }
}