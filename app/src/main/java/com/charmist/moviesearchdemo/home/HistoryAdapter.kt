package com.charmist.moviesearchdemo.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.charmist.moviesearchdemo.R
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(private val itemClick: (String) -> Unit) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    var values: List<String> = ArrayList(0)
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ViewHolder(
            viewHolder
        ) {
            itemClick(values[it])
        }
    }

    override fun getItemCount(): Int = values.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindUi(values[position])
    }

    class ViewHolder(view: View, private val itemClick: (Int) -> Unit) :
        RecyclerView.ViewHolder(view) {
        init {
            itemView.setOnClickListener {
                itemClick(adapterPosition)
            }
        }

        fun bindUi(name: String) {
            itemView.apply {
                tvTitle.text = name
            }
        }
    }

}