package com.example.tourismappdicodingmade.ui.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.tourismappdicodingmade.R
import com.example.tourismappdicodingmade.core.domain.model.Tourism
import com.example.tourismappdicodingmade.core.ui.ViewModelFactory
import com.example.tourismappdicodingmade.databinding.ActivityDetailTourismBinding

class DetailTourismActivity : AppCompatActivity() {

    private val detailTourismViewModel by viewModels<DetailTourismViewModel> { ViewModelFactory.getInstance(this) }
    private lateinit var binding: ActivityDetailTourismBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTourismBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        showDetailTourism()
    }

    private fun showDetailTourism() {
        with(intent.getParcelableExtra<Tourism>(EXTRA_DATA)) {
            this?.let { tourism ->
                supportActionBar?.title = tourism.name
                binding.content.tvDetailDescription.text = tourism.description

                Glide.with(this@DetailTourismActivity)
                    .load(tourism.image)
                    .into(binding.ivDetailImage)

                var statusFavorite = tourism.isFavorite
                setStatusFavorite(statusFavorite)

                binding.fab.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailTourismViewModel.setFavoriteTourism(tourism, statusFavorite)
                    setStatusFavorite(statusFavorite)
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite)
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white))
        else
            binding.fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_not_favorite_white))
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}