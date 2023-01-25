package com.example.tourismappdicodingmade.ui.favorite.adapter

import com.example.tourismappdicodingmade.core.domain.model.Tourism

interface OnItemClickListener {
    fun onItemClicked(tourism: Tourism)
}