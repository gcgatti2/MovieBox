package com.backbase.assignment.ui.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.backbase.assignment.R
import com.backbase.assignment.databinding.FragmentMovieDetailBinding
import com.backbase.assignment.ui.data.remote.entity.Movie
import com.backbase.assignment.ui.presentation.MovieViewModel
import com.backbase.assignment.ui.util.Either
import com.backbase.assignment.ui.util.generateImageUrl
import com.backbase.assignment.ui.util.getHourAndMinuteFromMinute
import com.backbase.assignment.ui.util.getLongformDate
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding
        get() = _binding!!
    private val movieViewModel: MovieViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { args->
            val movieId = args[MovieActivity.MOVIE_ID] as Long?
            if(movieId != null){
                movieViewModel.getMovieDetailsById(movieId).observe(viewLifecycleOwner,
                { result->
                    when(result){
                        is Either.Right -> {
                            bindMovieData(result.data)
                        }
                        is Either.Left -> { }
                    }
                })
            } else {
                val movie = args[MovieActivity.MOVIE_DETAILS] as Movie?
                movie?.let {
                    bindMovieData(it)
                }
            }
        }

        img_back_navigation.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun bindMovieData(movie: Movie){
        Glide
            .with(this)
            .load(movie.posterPath?.let{
                generateImageUrl(it)
            })
            .into(binding.imgPoster)
        binding.tvMovieTitle.text = movie.title
        binding.tvDateAndDuration.text = "${getLongformDate(movie.releaseDate)} - ${getHourAndMinuteFromMinute(movie.duration)}"
        binding.tvMovieDescription.text = movie.overview
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}