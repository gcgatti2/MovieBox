package com.backbase.assignment.ui.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.backbase.assignment.R
import com.backbase.assignment.ui.data.remote.entity.Movie
import com.backbase.assignment.ui.util.*
import com.bumptech.glide.Glide

class PopularMovieAdapter(private val movieClickListener: MovieClickListener?)
                          : PagingDataAdapter<Movie, PopularMovieAdapter.ViewHolder>(MovieComparator)  {

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
        .inflate(R.layout.popular_movie_item, parent, false)) {
        val imgPoster = itemView.findViewById<ImageView>(R.id.img_poster)
        val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
        val tvReleaseDate = itemView.findViewById<TextView>(R.id.tv_release_date)
        val tvDuration = itemView.findViewById<TextView>(R.id.tv_movie_duration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        movie?.let { movie->
            Glide
                .with(holder.itemView.context)
                .load(movie.posterPath?.let {
                    generateImageUrl(it, MINIMUM_FILE_SIZE)
                })
                .into(holder.imgPoster)
            holder.tvTitle.text = movie.title
            holder.tvReleaseDate.text = getLongformDate(movie.releaseDate)
            holder.tvDuration.text = getHourAndMinuteFromMinute(movie.duration)
            holder.itemView.setOnClickListener {
                movieClickListener?.onMovieClicked(movie.id)
            }
        }
    }

    companion object {
        private val MovieComparator = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
                oldItem == newItem
        }
    }
}