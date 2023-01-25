package com.example.tourismappdicodingmade.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tourismappdicodingmade.R
import com.example.tourismappdicodingmade.core.data.Resource
import com.example.tourismappdicodingmade.core.domain.model.Tourism
import com.example.tourismappdicodingmade.core.ui.ViewModelFactory
import com.example.tourismappdicodingmade.databinding.FragmentHomeBinding
import com.example.tourismappdicodingmade.ui.detail.DetailTourismActivity
import com.example.tourismappdicodingmade.ui.detail.DetailTourismActivity.Companion.EXTRA_DATA
import com.example.tourismappdicodingmade.ui.home.adapter.HomeTourismAdapter
import com.example.tourismappdicodingmade.ui.home.adapter.OnItemClickListener

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var tourismAdapter: HomeTourismAdapter
    private val homeViewModel by viewModels<HomeViewModel> { ViewModelFactory.getInstance(requireActivity()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            it.setTourismList()
            observeTourismData()
        }
    }

    private fun Activity.setTourismList() {
        tourismAdapter = HomeTourismAdapter(
            onItemClickListener = object : OnItemClickListener {
                override fun onItemClicked(tourism: Tourism) {
                    startActivity(
                        Intent(
                            this@setTourismList,
                            DetailTourismActivity::class.java
                        ).putExtra(
                            EXTRA_DATA,
                            tourism
                        )
                    )
                }
            }
        )

        with(binding!!.homeRvTourism) {
            layoutManager = LinearLayoutManager(this@setTourismList)
            setHasFixedSize(true)
            adapter = tourismAdapter
        }
    }

    private fun observeTourismData() {
        homeViewModel.tourism.observe(viewLifecycleOwner) { tourism ->
            tourism?.let {
                when(it) {
                    is Resource.Loading -> binding!!.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding!!.progressBar.visibility = View.GONE
                        tourismAdapter.setTourismData(tourism.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        binding!!.progressBar.visibility = View.GONE
                        binding!!.homeViewError.root.visibility = View.VISIBLE
                        binding!!.homeViewError.tvError.text = tourism.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}