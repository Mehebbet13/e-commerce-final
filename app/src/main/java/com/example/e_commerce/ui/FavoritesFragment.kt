package com.example.e_commerce.ui

import AppViewModel
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerce.ProductsRVAdapter
import com.example.e_commerce.R
import com.example.e_commerce.databinding.FragmentFavoritesBinding
import com.example.e_commerce.databinding.FragmentMainPageBinding
import com.example.e_commerce.observe


class FavoritesFragment : Fragment() {
    private lateinit var viewModelF: AppViewModel
    lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: ProductsRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelF = ViewModelProvider(requireActivity())
            .get(AppViewModel::class.java)
        initRecyclerView()
        observe(viewModelF.event, ::onViewEvent)

        viewModelF.products.observe(viewLifecycleOwner, {
            adapter.setList(it)
        })

    }
    private fun initRecyclerView() {
        binding.recycler.layoutManager =
            GridLayoutManager(context,2)
        adapter = ProductsRVAdapter(viewModelF)
        binding.recycler.adapter = adapter

    }
    private fun onViewEvent(event: AllProductsFragmentEvent?) {
        when (event) {
            is AllProductsFragmentEvent.OpenProductDetail -> {
                val action =
                    FavoritesFragmentDirections.actionFavoritesFragmentToProductDetailFragment(event.product)
                findNavController().navigate(action)
            }
        }
    }
}