package com.example.tourismappdicodingmade.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tourismappdicodingmade.core.data.source.remote.dto.ListTourismResponse
import com.example.tourismappdicodingmade.core.data.source.remote.dto.TourismResponse
import com.example.tourismappdicodingmade.core.data.source.remote.network.ApiResponse
import com.example.tourismappdicodingmade.core.data.source.remote.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {

    fun getAllTourism(): LiveData<ApiResponse<List<TourismResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<TourismResponse>>>()

        val client = apiService.getTourismList()
        client.enqueue(object : Callback<ListTourismResponse> {
            override fun onResponse(
                call: Call<ListTourismResponse>,
                response: Response<ListTourismResponse>
            ) {
                val data = response.body()?.places
                resultData.value = if (data.isNullOrEmpty()) ApiResponse.Empty else ApiResponse.Success(data)
            }

            override fun onFailure(call: Call<ListTourismResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultData
    }

    companion object {
        @Volatile
        private var INSTANCE: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: RemoteDataSource(apiService)
            }
    }
}