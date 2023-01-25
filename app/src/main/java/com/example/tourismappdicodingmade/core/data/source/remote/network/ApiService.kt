package com.example.tourismappdicodingmade.core.data.source.remote.network

import com.example.tourismappdicodingmade.core.data.source.remote.dto.ListTourismResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("list")
    fun getTourismList(): Call<ListTourismResponse>
}