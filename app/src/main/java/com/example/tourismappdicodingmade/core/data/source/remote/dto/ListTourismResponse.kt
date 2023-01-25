package com.example.tourismappdicodingmade.core.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class ListTourismResponse(
    @SerializedName("error")
    val error: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("places")
    val places: List<TourismResponse>
)