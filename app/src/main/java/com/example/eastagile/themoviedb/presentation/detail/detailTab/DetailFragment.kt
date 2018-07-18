package com.example.eastagile.themoviedb.presentation.detail.detailTab

import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.eastagile.themoviedb.R
import com.example.eastagile.themoviedb.data.Movie
import com.example.eastagile.themoviedb.data.Trailer
import com.example.eastagile.themoviedb.di.Injectable
import com.example.eastagile.themoviedb.extension.OnItemClickListener
import com.example.eastagile.themoviedb.extension.addOnItemClickListener
import com.example.eastagile.themoviedb.utils.AppConstant
import kotlinx.android.synthetic.main.fragment_detail.*
import java.text.SimpleDateFormat
import javax.inject.Inject
import javax.inject.Named

class DetailFragment : Fragment(), DetailContract.View, Injectable {

    private var dialog: Dialog? = null

    private var movie: Movie? = null

    @Inject
    @VisibleForTesting
    @field:Named("DetailPresenter")
    lateinit var presenter: DetailContract.Presenter

    companion object {
        fun newInstance(movie: Movie): Fragment {
            val fragment = DetailFragment()
            fragment.movie = movie
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter.attachView(this)
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        if (movie != null) {
            initView(movie!!)
            presenter.getTrailerList(movie!!.id)
        }
    }

    private fun initView(movie: Movie) {
        val posterUrl = AppConstant.POSTER_URL + movie.posterPath
        Glide.with(context!!).load(posterUrl).apply(RequestOptions.centerCropTransform()).into(imgvMovie)
        tvMovieName.text = movie.title
        tvDate.text = formatDate(movie.releaseDate)
        tvOverview.text = movie.overview
        initVote(movie)
        presenter.setFavourite(movie!!.id)
        fab.setOnClickListener {
            presenter.clickFavourite(movie)

        }
    }

    private fun initVote(movie: Movie) {
        val voteSum = 10
        for (i in 1..movie.voteAverage.toInt()) {
            val voteImage = ImageView(context)
            voteImage.layoutParams = ViewGroup.LayoutParams(40, 40)
            voteImage.setImageDrawable(ContextCompat.getDrawable(activity!!.applicationContext, R.drawable.ic_voted))
            voteLayout.addView(voteImage)
        }

        val unvoteSum = voteSum - movie.voteAverage.toInt()
        for (i in 1..unvoteSum) {
            val voteImage = ImageView(context)
            voteImage.layoutParams = ViewGroup.LayoutParams(40, 40)
            voteImage.setImageDrawable(ContextCompat.getDrawable(activity!!.applicationContext, R.drawable.ic_unvoted))
            voteLayout.addView(voteImage)
        }
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = DetailTrailerListAdapter(ArrayList(), context!!)
        recyclerView.setHasFixedSize(true)
        recyclerView.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                playVideo(position)
            }
        })
    }

    override fun setTrailerList(trailerList: ArrayList<Trailer>) {
        var adapter = recyclerView.adapter as DetailTrailerListAdapter
        adapter.setTrailerList(trailerList)
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.INVISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
        recyclerView.visibility = View.VISIBLE
    }

    override fun showErrorDialog(message: String) {
        if (dialog == null) {
            val builder = AlertDialog.Builder(context!!)
                    .setTitle(R.string.error)
                    .setMessage(message)
                    .setPositiveButton(R.string.ok, null)
            dialog = builder.create()
        }

        dialog!!.show()
    }

    override fun hideErrorDialog() {
        dialog?.dismiss()
    }

    override fun playVideo(position: Int) {
        var adapter = recyclerView.adapter as DetailTrailerListAdapter
        val youtubeKey = adapter.getTrailer(position).key
        val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + youtubeKey))
        val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(AppConstant.YOUTUBE_URL + youtubeKey))

        try {
            activity!!.startActivity(intentApp)
        } catch (ex: ActivityNotFoundException) {
            activity!!.startActivity(intentBrowser)
        }
    }

    override fun setFavourite(isFavoured: Boolean) {
        if (isFavoured) {
            fab.setImageDrawable(ContextCompat.getDrawable(activity!!, R.drawable.ic_favourite))
            fab.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(activity!!, R.color.colorAccent))
        } else {
            fab.setImageDrawable(ContextCompat.getDrawable(activity!!, R.drawable.ic_not_favourite))
            fab.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(activity!!, android.R.color.white))
        }
    }

    private fun formatDate(dateStr: String): String {
        val simpleFormat = SimpleDateFormat("yyyy-mm-dd")
        val date = simpleFormat.parse(dateStr)
        val dateFormat = SimpleDateFormat("MMMM yyyy")
        return dateFormat.format(date)
    }
}
