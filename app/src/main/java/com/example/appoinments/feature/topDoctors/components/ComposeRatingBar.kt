package com.example.appoinments.feature.topDoctors.components


import android.R
import android.content.res.ColorStateList
import android.widget.RatingBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun ComposeRatingBar(
    rating : Float,
    stars : Int = 5
){
    var starTint = Color(0xffffc160).toArgb()

    AndroidView(
        factory = {context ->
            RatingBar(context,null, R.attr.ratingBarStyleSmall).apply {
                setNumStars(stars)
                setStepSize(1f)
                setIsIndicator(true)
                progressTintList = ColorStateList.valueOf(starTint)
            }
        },
        update = {rb->
            rb.setRating(rating)
        }
    )
}