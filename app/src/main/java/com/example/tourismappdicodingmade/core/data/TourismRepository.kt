package com.example.tourismappdicodingmade.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.tourismappdicodingmade.core.data.source.local.LocalDataSource
import com.example.tourismappdicodingmade.core.data.source.remote.RemoteDataSource
import com.example.tourismappdicodingmade.core.data.source.remote.network.ApiResponse
import com.example.tourismappdicodingmade.core.data.source.remote.dto.TourismResponse
import com.example.tourismappdicodingmade.core.domain.ITourismRepository
import com.example.tourismappdicodingmade.core.domain.model.Tourism
import com.example.tourismappdicodingmade.core.utils.AppExecutors
import com.example.tourismappdicodingmade.core.utils.DataMapper

class TourismRepository private constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors
): ITourismRepository {

    override fun getAllTourism(): LiveData<Resource<List<Tourism>>> =
        object : NetworkBoundResources<List<Tourism>, List<TourismResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Tourism>> {
                return Transformations.map(localDataSource.getAllTourism()) {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Tourism>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TourismResponse>>> =
                remoteDataSource.getAllTourism()

            override fun saveCallResult(data: List<TourismResponse>) {
                val tourismList = DataMapper.mapResponseToEntities(data)
                localDataSource.insertTourism(tourismList)
            }
        }.asLiveData()

    override fun getFavoriteTourism(): LiveData<List<Tourism>> =
        Transformations.map(localDataSource.getFavoriteTourism()) {
            DataMapper.mapEntitiesToDomain(it)
        }

    override fun setFavoriteTourism(tourism: Tourism, state: Boolean) =
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteTourism(
                DataMapper.mapDomainToEntity(tourism),
                state
            )
        }

    companion object {
        @Volatile
        private var INSTANCE: TourismRepository? = null

        fun getInstance(
            localDataSource: LocalDataSource,
            remoteDataSource: RemoteDataSource,
            appExecutors: AppExecutors)
        : TourismRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: TourismRepository(
                    localDataSource, remoteDataSource, appExecutors
                )
            }
    }
}