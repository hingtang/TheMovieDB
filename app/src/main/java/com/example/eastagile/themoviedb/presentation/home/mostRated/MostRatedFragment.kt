package com.example.eastagile.themoviedb.presentation.home.mostRated

import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.eastagile.themoviedb.di.Injectable
import com.example.eastagile.themoviedb.presentation.home.base.BaseListContract
import com.example.eastagile.themoviedb.presentation.home.base.BaseListFragment
import javax.inject.Inject
import javax.inject.Named

class MostRatedFragment : BaseListFragment(), Injectable {

    @Inject
    @VisibleForTesting
    @field:Named("MostRatedPresenter")
    lateinit var presenter: BaseListContract.Presenter

    companion object {
        fun newInstance(): Fragment = MostRatedFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter.attachView(this)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getMovieList()
    }

}
