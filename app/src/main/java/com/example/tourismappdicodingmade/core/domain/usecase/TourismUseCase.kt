package com.example.tourismappdicodingmade.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.tourismappdicodingmade.core.data.Resource
import com.example.tourismappdicodingmade.core.domain.model.Tourism

interface TourismUseCase {
    fun getAllTourism(): LiveData<Resource<List<Tourism>>>
    fun getFavoriteTourism(): LiveData<List<Tourism>>
    fun setFavoriteTourism(tourism: Tourism, state: Boolean)
}