package com.backbase.assignment.ui.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.ui.data.remote.entity.BaseMovie
import com.backbase.assignment.ui.util.MINIMUM_FILE_SIZE
import com.backbase.assignment.ui.util.MovieClickListener
import com.backbase.assignment.ui.util.generateImageUrl
import com.bumptech.glide.Glide

class CurrentlyPlayingMoviePagingAdapter(private val movieClickListener: MovieClickListener?)
    : PagingDataAdapter<BaseMovie, CurrentlyPlayingMoviePagingAdapter.ViewHolder>(
    BaseMovieComparator) {

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.currently_playing_movie_item, parent, false)) {
        val context = itemView.context
        val imgPoster = itemView.findViewById<ImageView>(R.id.img_poster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let { baseMovie ->
            baseMovie.posterPath?.let {
                Glide
                    .with(holder.context)
                    .load(generateImageUrl(it, MINIMUM_FILE_SIZE))
                    .into(holder.imgPoster)
            }
            holder.itemView.setOnClickListener {
                movieClickListener?.onMovieClicked(baseMovie.id)
            }
        }
    }

    companion object {
        private val BaseMovieComparator = object : DiffUtil.ItemCallback<BaseMovie>() {
            override fun areItemsTheSame(oldItem: BaseMovie, newItem: BaseMovie) =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: BaseMovie, newItem: BaseMovie) =
                oldItem == newItem
        }
    }
}