package com.backbase.assignment.ui.presentation.screens

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.backbase.assignment.R
import com.backbase.assignment.databinding.FragmentMovieDetailBinding
import com.backbase.assignment.ui.data.remote.entity.Movie
import com.backbase.assignment.ui.presentation.MovieViewModel
import com.backbase.assignment.ui.presentation.adapter.GenreListAdapter
import com.backbase.assignment.ui.presentation.util.*
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding
        get() = _binding!!
    private val movieViewModel: MovieViewModel by activityViewModels()
    private lateinit var genreListAdapter: GenreListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { args->
            val movie = args.get(MovieActivity.MOVIE_DETAILS) as Movie?
            if(movie != null) {
                bindMovieData(movie)
            } else {
                // Movie ID must exist if the Movie itself was not passed in
                val movieId = args.getLong(MovieActivity.MOVIE_ID)
                // Tries to retrieve the network result from SaveStateHandle
                movieViewModel.setCurrentMovieLiveDataFromSavedStateHandle(movieId)
                val currentMovieLiveDataValue = movieViewModel.currentMovieLiveData.value

                //no cached value for this ID exists as of now. if the value was a network failure, try again.
                if(currentMovieLiveDataValue == null || movieViewModel.currentMovieLiveData is Either.Left<*, *>) {
                    EspressoIdlingResource.increment()
                    observeMovieLiveData()
                    movieViewModel.getMovieDetailsById(movieId)
                    //this will retrieve the movie details and cache the result
                } else {
                    parseMovieResult(currentMovieLiveDataValue)
                }
            }
        }

        img_back_navigation.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun observeMovieLiveData() {
        movieViewModel.currentMovieLiveData.observe(viewLifecycleOwner, {
            it?.let { result->
                parseMovieResult(result)
            }
            EspressoIdlingResource.decrement()
        })
    }

    private fun parseMovieResult(result: Either<Failure, Movie>) {
        when(result) {
            is Either.Right -> {
                bindMovieData(result.data)
            }
            is Either.Left -> { }
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
        genreListAdapter = GenreListAdapter(movie.genres)
        val manager = FlexboxLayoutManager(context, FlexDirection.ROW, FlexWrap.WRAP)
        binding.rvGenreList.apply{
            layoutManager = manager
            adapter = genreListAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}