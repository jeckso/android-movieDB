package com.jeckso.moviedb.presentation.main.films.implementation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeckso.moviedb.R
import com.jeckso.moviedb.android.list.ItemClickListener
import com.jeckso.moviedb.android.list.decorators.LinearItemMarginDecoration
import com.jeckso.moviedb.presentation.base.implementation.view.fragment.BaseProgressiveFragment
import com.jeckso.moviedb.presentation.main.films.interfaces.FilmListView
import com.jeckso.moviedb.presentation.main.films.utils.FilmListAdapter
import moxy.ktx.moxyPresenter
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class FilmListFragment : BaseProgressiveFragment(), FilmListView, ItemClickListener<FilmVM> {

    @Inject
    lateinit var provider: Provider<FilmListPresenter>

    private val presenter by moxyPresenter { provider.get() }
    private val adapter: FilmListAdapter = FilmListAdapter(this)

    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        applicationComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val margin = resources.getDimensionPixelSize(R.dimen.margin_8)
        emptyView = view.findViewById(R.id.layout_empty_view)
        recyclerView = view.findViewById(R.id.layout_list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context!!)
        recyclerView.addItemDecoration(LinearItemMarginDecoration(space = margin, outBound = true))
    }

    override val layoutResId: Int = R.layout.fragment_films_list

    override fun onFilmsReady(list: List<FilmVM>) {
        adapter.items = list
        emptyView.isVisible = false
        recyclerView.isVisible = true
    }

    override fun onFilmsNotFound() {
        emptyView.isVisible = true
        recyclerView.isVisible = false
    }

    override fun navigateToFilm(filmVM: FilmVM) {
        Timber.e("FILM $filmVM")
    }

    override fun onItemClick(item: FilmVM, position: Int) {
        presenter.navigateToFilm(item)
    }

}