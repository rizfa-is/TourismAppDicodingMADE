package com.example.tourismappdicodingmade.core.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tourismappdicodingmade.core.di.TourismModule
import com.example.tourismappdicodingmade.core.domain.usecase.TourismUseCase
import com.example.tourismappdicodingmade.ui.detail.DetailTourismViewModel
import com.example.tourismappdicodingmade.ui.favorite.FavoriteViewModel
import com.example.tourismappdicodingmade.ui.home.HomeViewModel

class ViewModelFactory private constructor(private val tourismUseCase: TourismUseCase)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(tourismUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(tourismUseCase) as T
            }
            modelClass.isAssignableFrom(DetailTourismViewModel::class.java) -> {
                DetailTourismViewModel(tourismUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(TourismModule.provideTourismUseCase(context))
            }
    }
}