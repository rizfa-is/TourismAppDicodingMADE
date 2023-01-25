package com.example.tourismappdicodingmade.ui.favorite

import androidx.lifecycle.ViewModel
import com.example.tourismappdicodingmade.core.domain.usecase.TourismUseCase

class FavoriteViewModel(tourismUseCase: TourismUseCase): ViewModel() {
    val favoriteTourism = tourismUseCase.getFavoriteTourism()
}