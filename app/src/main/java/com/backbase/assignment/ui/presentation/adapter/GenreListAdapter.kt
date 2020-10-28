package com.backbase.assignment.ui.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.ui.data.remote.entity.Genre
import com.google.android.material.button.MaterialButton

class GenreListAdapter(private val genres: List<Genre>): RecyclerView.Adapter<GenreListAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val genreBox = view.findViewById<MaterialButton>(R.id.btn_genre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.genre_box_layout, parent, false))

    override fun getItemCount() = genres.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.genreBox.text = genres[position].name
    }
}