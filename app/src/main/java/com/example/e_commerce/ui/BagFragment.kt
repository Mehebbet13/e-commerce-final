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
import com.example.e_commerce.BagProductsRVAdapter
import com.example.e_commerce.ProductsRVAdapter
import com.example.e_commerce.databinding.FragmentBagBinding
import com.example.e_commerce.observe
import kotlinx.android.synthetic.main.bug_product.*

class BagFragment : Fragment() {
    private lateinit var viewModel: AppViewModel
    lateinit var binding: FragmentBagBinding
    private lateinit var adapter: BagProductsRVAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBagBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())
            .get(AppViewModel::class.java)
        initRecyclerView()
        observe(viewModel.event, ::onViewEvent)

        viewModel.bagProducts.observe(viewLifecycleOwner, {
            adapter.setList(it)
        })
    }
    private fun initRecyclerView() {
        binding.recyclerBag.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapter = BagProductsRVAdapter(viewModel)
        binding.recyclerBag.adapter = adapter
    }
    private fun onViewEvent(event: AllProductsFragmentEvent?) {
        when (event) {
            is AllProductsFragmentEvent.OpenProductDetail -> {
                val action =
                    BagFragmentDirections.actionBagFragmentToProductDetailFragment(event.product)
                findNavController().navigate(action)
            }
        }
    }
}