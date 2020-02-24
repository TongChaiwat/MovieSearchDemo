package com.charmist.moviesearchdemo.movie.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.charmist.moviesearchdemo.R
import com.charmist.moviesearchdemo.model.Movie

class MovieAdapter(private val itemClick: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieViewHolder>() {

    var values: MutableList<Movie> = ArrayList(0)
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private var lastPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(
            viewHolder
        ) {
            itemClick(values[it])
        }
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindUi(values[position])
        if (position > lastPosition) {
            holder.itemView.startAnimation(
                AnimationUtils.loadAnimation(holder.itemView.context, R.anim.slide_up)
            )
            lastPosition = position
        }
    }

    fun addMovieList(itemList: MutableList<Movie>) {
        values.addAll(values.size, itemList)
        notifyDataSetChanged()
    }
}