package com.example.tourismappdicodingmade.core.utils

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.example.tourismappdicodingmade.R

object ImageUtils {
    fun ImageView.loadImage(
        context: Context,
        image: String,
        @DrawableRes placeholder: Int = R.drawable.ic_baseline_image_24,
        @DrawableRes errorImage: Int = R.drawable.ic_baseline_broken_image_24
    ) {
        Glide
            .with(context)
            .load(image)
            .placeholder(placeholder)
            .error(errorImage)
            .into(this)
    }
}