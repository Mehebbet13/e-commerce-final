package com.example.e_commerce.ui

import AppViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e_commerce.Product
import com.example.e_commerce.ProductsRVAdapter
import com.example.e_commerce.databinding.FragmentAllProductsBinding
import com.example.e_commerce.observe
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AllProductsFragment : Fragment() {
    lateinit var binding: FragmentAllProductsBinding
    private lateinit var adapter: ProductsRVAdapter
    private lateinit var adapterNew: ProductsRVAdapter
    private lateinit var viewModel: AppViewModel
    private var products = arrayListOf<Product>()
    private var productsSale = arrayListOf<Product>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= ViewModelProvider(this)
            .get(AppViewModel::class.java)
        getData()
        initRecyclerView()
        observe(viewModel.event, ::onViewEvent)
    }

    private fun initRecyclerView() {
        binding.recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = ProductsRVAdapter(viewModel)
        binding.recycler.adapter = adapter

        binding.recyclerNew.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapterNew = ProductsRVAdapter(viewModel)
        binding.recyclerNew.adapter = adapterNew

    }

    private fun getData() {
        val database = FirebaseDatabase.getInstance().reference
        val getData = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (it in snapshot.children) {
                    for (sp in it.child("Skirt/women").children) {
                        var product = sp.getValue(Product::class.java)
                        if (product != null && product.new == true) {
                            products.add(product)
                        }else if (product != null && product.onSale?.sale == true) {
                            productsSale.add(product)
                        }
                    }
                    for (sp in it.child("Dresses/women").children) {
                        var product = sp.getValue(Product::class.java)
                        if (product != null && product.new == true) {
                            products.add(product)
                        }else if (product != null && product.onSale?.sale == true) {
                            productsSale.add(product)
                        }
                    }
                    for (sp in it.child("Shorts/women").children) {
                        var product = sp.getValue(Product::class.java)
                        if (product != null && product.new == true) {
                            products.add(product)
                        }else if (product != null && product.onSale?.sale == true) {
                            productsSale.add(product)
                        }
                    }
                    for (sp in it.child("Shorts/men").children) {
                        var product = sp.getValue(Product::class.java)
                        if (product != null && product.new == true) {
                            products.add(product)
                        }else if (product != null && product.onSale?.sale == true) {
                            productsSale.add(product)
                        }
                    }
                    for (sp in it.child("Trousers/women").children) {
                        var product = sp.getValue(Product::class.java)
                        if (product != null && product.new == true) {
                            products.add(product)
                        }else if (product != null && product.onSale?.sale == true) {
                            productsSale.add(product)
                        }
                    }
                    for (sp in it.child("Trousers/men").children) {
                        var product = sp.getValue(Product::class.java)
                        if (product != null && product.new == true) {
                            products.add(product)
                        }else if (product != null && product.onSale?.sale == true) {
                            productsSale.add(product)
                        }
                    }
                    for (sp in it.child("T_shirts/women").children) {
                        var product = sp.getValue(Product::class.java)
                        if (product != null && product.new == true) {
                            products.add(product)
                        }else if (product != null && product.onSale?.sale == true) {
                            productsSale.add(product)
                        }
                    }
                    for (sp in it.child("T_shirts/men").children) {
                        var product = sp.getValue(Product::class.java)
                        if (product != null && product.new == true) {
                            products.add(product)
                        } else if (product != null && product.onSale?.sale == true) {
                            productsSale.add(product)
                        }
                    }
                    break

                }
                adapterNew.setList(products)
                adapter.setList(productsSale)
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
                val action = AllProductsFragmentDirections.actionAllProductsFragmentToProductDetailFragment(event.product)
                findNavController().navigate(action)
            }
        }
    }
}