package com.backbase.assignment.ui

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.RotateDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.backbase.assignment.R

class MyRatingView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    companion object {
        const val HIGHER_REVIEW_SCORE_THRESHOLD = 50 //point at which the review is a "higher score"
    }

    init {
        init(attrs)
    }

    var rating = 0
    set(r) {
        field = r
        adjustRating(field)
    }
    private lateinit var progressBar: ProgressBar
    private lateinit var tvRatingView: TextView
    private val ratingRingDrawable by lazy { ContextCompat.getDrawable(context, R.drawable.rating_ring) }
    private val circularShapeDrawable by lazy {
        ((ratingRingDrawable as LayerDrawable).findDrawableByLayerId(R.id.circular_progress) as RotateDrawable).drawable as GradientDrawable
    }

    private fun init(attrs: AttributeSet) {
        val view = LayoutInflater.from(context).inflate(R.layout.rating_view_layout, this, false)
        addView(view)
        progressBar = view.findViewById(R.id.progress_bar)
        tvRatingView = view.findViewById(R.id.tv_rating)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyRatingView, 0, 0)
        rating = typedArray.getInt(R.styleable.MyRatingView_rating, 0)
        adjustRating(rating)
        typedArray.recycle()
    }

    private fun adjustRating(newRating: Int) {
        if(newRating >= HIGHER_REVIEW_SCORE_THRESHOLD) {
            circularShapeDrawable.setColor(ContextCompat.getColor(context, R.color.lightGreen))
        } else {
            circularShapeDrawable.setColor(ContextCompat.getColor(context, R.color.mustardYellow))
        }
        progressBar.apply {
            progressDrawable = ratingRingDrawable
            progress = newRating
        }
        tvRatingView.text = newRating.toString()
    }
}