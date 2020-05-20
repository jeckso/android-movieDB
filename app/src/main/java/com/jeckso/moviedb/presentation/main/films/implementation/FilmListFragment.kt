package com.jeckso.moviedb.presentation.main.films.implementation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.input.input
import com.afollestad.materialdialogs.list.listItemsSingleChoice
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
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_skip -> {
                presenter.showSearchDialog()
                return true
            }
            else -> false
        }
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

    override fun showSearchDialog(genres: List<CharSequence>) {
        MaterialDialog(activity!!, BottomSheet(LayoutMode.WRAP_CONTENT)).show {
            title(R.string.title_search)
            input(hintRes = R.string.film_name, waitForPositiveButton = true) { _, text ->
                presenter.filmName = text.toString()
            }
            listItemsSingleChoice(
                items = genres,
                waitForPositiveButton = true
            ) { _, _, text ->
                presenter.genre = text.toString()
            }
            listItemsSingleChoice(
                items = (1..5).map { it.toString() },
                waitForPositiveButton = true
            ) { _, index, _ ->
                presenter.rating = index + 1
            }
            negativeButton(res = R.string.clear_filters) {
                presenter.clearFiltering()
                it.dismiss()
            }
            positiveButton {
                presenter.search()
                it.dismiss()
            }
        }
    }

    override fun onItemClick(item: FilmVM, position: Int) {
        presenter.navigateToFilm(item)
    }

}