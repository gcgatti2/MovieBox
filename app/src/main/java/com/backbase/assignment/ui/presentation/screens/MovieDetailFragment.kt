package com.backbase.assignment.ui.presentation.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.backbase.assignment.R
import com.backbase.assignment.databinding.FragmentMovieDetailBinding
import com.backbase.assignment.ui.data.remote.entity.Movie
import com.backbase.assignment.ui.util.getHourAndMinuteFromMinute
import com.backbase.assignment.ui.util.getLongformDate
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var binding: FragmentMovieDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { args->
            args[MovieActivity.MOVIE_ID]
        }

        img_back_navigation.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    fun bindMovieData(movie: Movie){
        Glide
            .with(this)
            .load(movie.posterPath)
            .into(binding.imgPoster)
        binding.tvMovieTitle.text = movie.title
        binding.tvDateAndDuration.text =
            "${getLongformDate(movie.releaseDate)} - ${getHourAndMinuteFromMinute(movie.duration)}"
        binding.tvMovieDescription.text = movie.overview
    }
}