package com.jeckso.moviedb.presentation.main.films.utils

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jeckso.moviedb.BuildConfig
import com.jeckso.moviedb.R
import com.jeckso.moviedb.android.list.BaseViewHolder
import com.jeckso.moviedb.android.list.ItemClickListener
import com.jeckso.moviedb.presentation.main.films.implementation.FilmVM

class FilmVH(view: View) : BaseViewHolder<FilmVM>(view) {

    private val titleTextView: TextView = view.findViewById(R.id.text_title)
    private val subtitleTextView: TextView = view.findViewById(R.id.text_subtitle)
    private val contentTextView: TextView = view.findViewById(R.id.text_content)
    private val previewImageView: ImageView = view.findViewById(R.id.image_film_preview)
    private val actionViewButton: Button = view.findViewById(R.id.button_action_view)

    override fun bindView(item: FilmVM, itemClickListener: ItemClickListener<FilmVM>?) {
        super.bindView(item, itemClickListener)
        titleTextView.text = item.title
        subtitleTextView.text = item.stringGenres
        contentTextView.text = item.overview
        Glide.with(itemView.context)
            .load(item.previewUrl)
            .error( R.drawable.ic_baseline_tv_off_24)
            .placeholder( R.drawable.ic_baseline_tv_off_24)
            .into(previewImageView)
        actionViewButton.setOnClickListener {
            itemClickListener?.onItemClick(
                item,
                adapterPosition
            )
        }
    }

    private val FilmVM.stringGenres: String
        get() = genres.map { it.name }.joinToString()

    private val FilmVM.previewUrl: String
        get() = BuildConfig.IMAGE_DOMAIN + posterImage


    override fun unbindView() {
        super.unbindView()
        actionViewButton.setOnClickListener(null)
    }

}