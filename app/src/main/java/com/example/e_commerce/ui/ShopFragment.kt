package com.example.e_commerce.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.e_commerce.R
import kotlinx.android.synthetic.main.fragment_shop.*

class ShopFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        category1.setOnClickListener {
            val action = ShopFragmentDirections.actionShopFragmentToCategorizedProductsFragment("New")
            findNavController().navigate(action)

        }
        saleSlogan.setOnClickListener {
            val action = ShopFragmentDirections.actionShopFragmentToCategorizedProductsFragment("Sale")
            findNavController().navigate(action)

        }
        category2.setOnClickListener {
            val action = ShopFragmentDirections.actionShopFragmentToCategoriesFragment("Clothes")
            findNavController().navigate(action)

        }
        category3.setOnClickListener {
            val action = ShopFragmentDirections.actionShopFragmentToCategorizedProductsFragment("Shoes")
            findNavController().navigate(action)

        }
    }
}