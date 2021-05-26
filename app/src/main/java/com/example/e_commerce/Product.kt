package com.example.e_commerce

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    var brandName: String? = null,
    var colors: ArrayList<String>? = arrayListOf(),
    var count: Int? = null,
    var id: String? = null,
    var imagesUrls: ArrayList<String>? = null,
    var name: String? = null,
    var about: String? = null,
    var new: Boolean? = false,
    var onSale: Sale? = null,
    var price: Double? = null,
    var fav: Boolean = false,
    var inBag: Boolean = false,
    var sizes: ArrayList<String>? = arrayListOf(),
    var color: String? = null,
    var sizeSelected: String? = null,
//    var size: ArrayList<String>? = null,
) : Parcelable

@Parcelize
data class Sale(
    var sale: Boolean? = false,
    var percentage: Int? = null,
    ) : Parcelable