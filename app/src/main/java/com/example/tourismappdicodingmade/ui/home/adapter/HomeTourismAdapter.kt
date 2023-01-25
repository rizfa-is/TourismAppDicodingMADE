package com.example.tourismappdicodingmade.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tourismappdicodingmade.core.domain.model.Tourism
import com.example.tourismappdicodingmade.core.utils.ImageUtils.loadImage
import com.example.tourismappdicodingmade.databinding.HomeItemListTourismBinding

class HomeTourismAdapter(private val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<HomeTourismAdapter.ViewHolder>() {

    private val listTourism = arrayListOf<Tourism>()

    fun setTourismData(data: List<Tourism>) {
        listTourism.clear()
        listTourism.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: HomeItemListTourismBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tourism: Tourism) {
            binding.ivItemImage.loadImage(
                binding.ivItemImage.context,
                tourism.image
            )
            binding.tvItemTitle.text = tourism.name
            binding.tvItemSubtitle.text = tourism.address
            binding.root.setOnClickListener { onItemClickListener.onItemClicked(tourism) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HomeItemListTourismBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listTourism[position])
    }

    override fun getItemCount(): Int = listTourism.size

}