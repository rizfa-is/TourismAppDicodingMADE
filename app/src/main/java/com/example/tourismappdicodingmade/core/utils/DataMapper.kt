package com.example.tourismappdicodingmade.core.utils

import com.example.tourismappdicodingmade.core.data.source.local.entity.TourismEntity
import com.example.tourismappdicodingmade.core.data.source.remote.dto.TourismResponse
import com.example.tourismappdicodingmade.core.domain.model.Tourism

object DataMapper {
    fun mapResponseToEntities(input: List<TourismResponse>): List<TourismEntity> {
        return input.map {
            TourismEntity(
                it.id,
                it.name,
                it.description,
                it.address,
                it.latitude,
                it.longitude,
                it.like,
                it.image,
                false
            )
        }
    }

    fun mapEntitiesToDomain(input: List<TourismEntity>): List<Tourism> {
        return input.map {
            Tourism(
                it.tourismId,
                it.name,
                it.description,
                it.address,
                it.latitude,
                it.longitude,
                it.like,
                it.image,
                it.isFavorite
            )
        }
    }

    fun mapDomainToEntity(input: Tourism): TourismEntity {
        return input.let {
            TourismEntity(
                it.tourismId,
                it.name,
                it.description,
                it.address,
                it.latitude,
                it.longitude,
                it.like,
                it.image,
                it.isFavorite
            )
        }
    }
}