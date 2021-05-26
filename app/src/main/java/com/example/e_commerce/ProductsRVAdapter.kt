package com.example.e_commerce

import AppViewModel
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_commerce.databinding.ProductBinding

class ProductsRVAdapter(
    val viewModel: AppViewModel
) : RecyclerView.Adapter<MyViewHolder>() {
    private val productsList = ArrayList<Product>()
    var productC = Product()

    fun setList(products: List<Product>) {
        productsList.clear()
        productsList.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ProductBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.product, parent, false)
        return MyViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        productC = holder.productP
        holder.bind(productsList[position])
    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}

class MyViewHolder(
    val binding: ProductBinding, val viewModel: AppViewModel
) : RecyclerView.ViewHolder(binding.root) {
    var productP = Product()

    fun bind(product: Product) {
        binding.name.text = product.brandName
        binding.description.text = product.name
        binding.price.text = "${product.price}$"
        binding.productConst.setOnClickListener {
            viewModel.onClickedProduct(product)

        }
        if (product.fav) binding.favorite.setColorFilter(Color.parseColor("#FF4500"))
        binding.favorite.setOnClickListener {
            if (!product.fav) {
                viewModel.onFavoriteProduct(product)
                binding.favorite.setColorFilter(Color.parseColor("#FF4500"))
            }
            else{
                viewModel.deleteFavProduct(product)
            }
        }
        val imageURL = product.imagesUrls?.get(0)!!
        Glide.with(binding.cardImage.context)
            .load(imageURL)
            .into(binding.cardImage)
    }
}