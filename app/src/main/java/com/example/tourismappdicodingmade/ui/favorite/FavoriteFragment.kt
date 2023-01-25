package com.example.tourismappdicodingmade.ui.favorite

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tourismappdicodingmade.core.domain.model.Tourism
import com.example.tourismappdicodingmade.core.ui.ViewModelFactory
import com.example.tourismappdicodingmade.databinding.FragmentFavoriteBinding
import com.example.tourismappdicodingmade.ui.detail.DetailTourismActivity
import com.example.tourismappdicodingmade.ui.favorite.adapter.FavoriteTourismAdapter
import com.example.tourismappdicodingmade.ui.favorite.adapter.OnItemClickListener

class FavoriteFragment : Fragment() {

    private val favoriteViewModel by viewModels<FavoriteViewModel> { ViewModelFactory.getInstance(requireActivity()) }
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var tourismAdapter: FavoriteTourismAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            it.setTourismList()
            observeTourismData()
        }
    }

    private fun Activity.setTourismList() {
        tourismAdapter = FavoriteTourismAdapter(
            onItemClickListener = object : OnItemClickListener {
                override fun onItemClicked(tourism: Tourism) {
                    startActivity(
                        Intent(
                            this@setTourismList,
                            DetailTourismActivity::class.java
                        ).putExtra(
                            DetailTourismActivity.EXTRA_DATA,
                            tourism
                        )
                    )
                }
            }
        )

        with(binding.favoriteRvTourism) {
            layoutManager = LinearLayoutManager(this@setTourismList)
            setHasFixedSize(true)
            adapter = tourismAdapter
        }
    }

    private fun observeTourismData() {
        favoriteViewModel.favoriteTourism.observe(viewLifecycleOwner) {
            tourismAdapter.setTourismData(it)
            binding.favoriteViewEmpty.root.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        }
    }
}
