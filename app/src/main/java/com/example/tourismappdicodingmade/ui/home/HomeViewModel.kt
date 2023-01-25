package com.example.tourismappdicodingmade.ui.home

import androidx.lifecycle.ViewModel
import com.example.tourismappdicodingmade.core.domain.usecase.TourismUseCase

class HomeViewModel(private val tourismUseCase: TourismUseCase): ViewModel() {
    val tourism = tourismUseCase.getAllTourism()
}