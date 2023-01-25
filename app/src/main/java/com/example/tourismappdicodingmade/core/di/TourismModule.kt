package com.example.tourismappdicodingmade.core.di

import android.content.Context
import com.example.tourismappdicodingmade.core.data.TourismRepository
import com.example.tourismappdicodingmade.core.data.source.local.LocalDataSource
import com.example.tourismappdicodingmade.core.data.source.local.room.TourismDatabase
import com.example.tourismappdicodingmade.core.data.source.remote.RemoteDataSource
import com.example.tourismappdicodingmade.core.data.source.remote.network.ApiConfig
import com.example.tourismappdicodingmade.core.domain.ITourismRepository
import com.example.tourismappdicodingmade.core.domain.usecase.TourismInteractor
import com.example.tourismappdicodingmade.core.domain.usecase.TourismUseCase
import com.example.tourismappdicodingmade.core.utils.AppExecutors

object TourismModule {
    private fun provideRepository(context: Context): ITourismRepository {
        val database = TourismDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiConfig())
        val localDataSource = LocalDataSource.getInstance(database.tourismDao())
        val appExecutors = AppExecutors()

        return TourismRepository.getInstance(localDataSource, remoteDataSource, appExecutors)
    }

    fun provideTourismUseCase(context: Context): TourismUseCase {
        val repository = provideRepository(context)

        return TourismInteractor(repository)
    }
}