package com.example.tourismappdicodingmade.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.tourismappdicodingmade.core.data.Resource
import com.example.tourismappdicodingmade.core.domain.ITourismRepository
import com.example.tourismappdicodingmade.core.domain.model.Tourism

class TourismInteractor(private val tourismRepository: ITourismRepository): TourismUseCase {

    override fun getAllTourism(): LiveData<Resource<List<Tourism>>> =
        tourismRepository.getAllTourism()

    override fun getFavoriteTourism(): LiveData<List<Tourism>> =
        tourismRepository.getFavoriteTourism()

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) =
        tourismRepository.setFavoriteTourism(tourism, state)
}