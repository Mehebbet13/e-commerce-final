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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerce.Product
import com.example.e_commerce.ProductsRVAdapter
import com.example.e_commerce.databinding.FragmentMainPageBinding
import com.example.e_commerce.observe
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_main_page.*
import org.json.JSONObject
import kotlin.check

class MainPageFragment : Fragment() {
    lateinit var binding: FragmentMainPageBinding
    private lateinit var adapter: ProductsRVAdapter
    private var products = arrayListOf<Product>()
    private lateinit var viewModel: AppViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())
            .get(AppViewModel::class.java)
        getData()
        initRecyclerView()
        check.setOnClickListener {
            val action = MainPageFragmentDirections.actionMainPageFragmentToAllProductsFragment()
            findNavController().navigate(action)
        }
        observe(viewModel.event, ::onViewEvent)
//        viewModel.products.observe(viewLifecycleOwner, {
//            Log.e("mike obs", it.toString())
//        })
    }

    private fun initRecyclerView() {
        binding.recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = ProductsRVAdapter(viewModel)
        binding.recycler.adapter = adapter
    }

    private fun getData() {
        val database = FirebaseDatabase.getInstance().reference
        val getData = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (it in snapshot.children) {
                    for (sp in it.child("Dresses/women").children) {
                        var product = sp.getValue(Product::class.java)
                        if (product != null) {
                            products.add(product)
                        }
                    }
                    for (sp in it.child("Shoes/women").children) {
                        var product = sp.getValue(Product::class.java)
                        if (product != null) {
                            products.add(product)
                        }
                    }
                    break

                }
                adapter.setList(products)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        database.addValueEventListener(getData)
        database.addListenerForSingleValueEvent(getData)
    }

    private fun onViewEvent(event: AllProductsFragmentEvent?) {
        when (event) {
            is AllProductsFragmentEvent.OpenProductDetail -> {
                val action =
                    MainPageFragmentDirections.actionMainPageFragmentToProductDetailFragment(event.product)
                findNavController().navigate(action)
            }
        }
    }
}