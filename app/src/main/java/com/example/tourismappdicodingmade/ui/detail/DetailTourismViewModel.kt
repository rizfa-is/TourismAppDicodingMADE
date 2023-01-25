package com.example.tourismappdicodingmade.ui.detail

import androidx.lifecycle.ViewModel
import com.example.tourismappdicodingmade.core.domain.usecase.TourismUseCase
import com.example.tourismappdicodingmade.core.domain.model.Tourism

class DetailTourismViewModel(private val tourismUseCase: TourismUseCase): ViewModel() {
    fun setFavoriteTourism(tourism: Tourism, newStatus: Boolean) = tourismUseCase.setFavoriteTourism(tourism, newStatus)
}