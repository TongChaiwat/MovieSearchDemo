package com.charmist.moviesearchdemo.movie.movielist

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.charmist.moviesearchdemo.R
import com.charmist.moviesearchdemo.model.Movie
import com.charmist.moviesearchdemo.utils.Constant
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieViewHolder(view: View, private val itemClick: (Int) -> Unit) :
    RecyclerView.ViewHolder(view) {
    private val tvTitle = itemView.tvTitle
    private val tvDate = itemView.tvDate
    private val tvDescription = itemView.tvDescription
    private val ivPoster = itemView.ivPoster
    private val context: Context by lazy { itemView.context }

    init {
        itemView.setOnClickListener {
            itemClick(adapterPosition)
        }
    }

    fun bindUi(movie: Movie) {
        tvTitle.text = movie.title
        tvDate.text = movie.release_date
        tvDescription.text = movie.overview
        Glide.with(context)
            .load(Constant.BASE_IMAGE_URL + movie.poster_path)
            //  .placeholder(R.drawable.ic_loading_image)
            .error(R.drawable.ic_no_image)
            .into(ivPoster)
    }
}