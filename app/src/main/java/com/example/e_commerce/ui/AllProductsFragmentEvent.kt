package com.example.e_commerce.ui

import com.example.e_commerce.Product

sealed class AllProductsFragmentEvent {
    data class OpenProductDetail(val product: Product) : AllProductsFragmentEvent()

}