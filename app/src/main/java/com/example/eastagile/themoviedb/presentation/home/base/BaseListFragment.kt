package com.example.eastagile.themoviedb.presentation.home.base

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eastagile.themoviedb.R
import com.example.eastagile.themoviedb.data.Movie
import com.example.eastagile.themoviedb.di.Injectable
import com.example.eastagile.themoviedb.extension.OnItemClickListener
import com.example.eastagile.themoviedb.extension.addOnItemClickListener
import com.example.eastagile.themoviedb.presentation.detail.DetailActivity
import com.example.eastagile.themoviedb.presentation.home.HomeActivity
import com.example.eastagile.themoviedb.server.RequestInterface
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_base_list.*
import javax.inject.Inject

open abstract class BaseListFragment : Fragment(), BaseListContract.View, Injectable {

    private var dialog: Dialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_base_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        setFavouriteList((activity as HomeActivity).favouriteList)
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = MovieListAdapter(ArrayList(), ArrayList(), context!!)
        recyclerView.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                navigateToMovieDetail(position)
            }
        })
    }

    override fun navigateToMovieDetail(position: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        var adapter = recyclerView.adapter as MovieListAdapter
        intent.putExtra("MOVIE", Gson().toJson(adapter.getMovieList()[position]))
        startActivity(intent)
    }

    override fun setMovieList(movieList: ArrayList<Movie>) {
        var adapter = recyclerView.adapter as MovieListAdapter
        adapter.setMovieList(movieList)
    }

    override fun setFavouriteList(movieList: ArrayList<Movie>) {
        var adapter = recyclerView.adapter as MovieListAdapter
        adapter.setFavouriteList(movieList)
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