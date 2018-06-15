package com.example.eastagile.themoviedb.presentation.detail.reviewTab

import android.app.Dialog
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eastagile.themoviedb.R
import com.example.eastagile.themoviedb.data.Review
import com.example.eastagile.themoviedb.di.Injectable
import kotlinx.android.synthetic.main.fragment_review.*
import javax.inject.Inject
import javax.inject.Named

class ReviewFragment : Fragment(), ReviewContract.View, Injectable {

    private var dialog: Dialog? = null

    @Inject
    @VisibleForTesting
    @field:Named("ReviewPresenter")
    lateinit var presenter: ReviewContract.Presenter

    private var movieId: Int = -1

    companion object {
        fun newInstance(movieId: Int): Fragment {
            val fragment = ReviewFragment()
            fragment.movieId = movieId
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter.attachView(this)
        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        if (movieId != -1) {
            presenter.getReviewList(movieId)
        }
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ReviewListAdapter(ArrayList(), context!!)
        recyclerView.setHasFixedSize(true)
    }

    override fun setReviewList(reviewList: ArrayList<Review>) {
        var adapter = recyclerView.adapter as ReviewListAdapter
        adapter.setReviewList(reviewList)
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
}
