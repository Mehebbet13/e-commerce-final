package com.example.e_commerce.ui

import AppViewModel
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.e_commerce.R
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.fragment_product_detail.*


class ProductDetailFragment : Fragment() {
    var carouselView: CarouselView? = null
    private val args: ProductDetailFragmentArgs by navArgs()
    private val colors = arrayListOf<String>()
    private val sizes = arrayListOf<String>()
    private lateinit var viewModel: AppViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())
            .get(AppViewModel::class.java)

        args.product.colors?.let { colors.addAll(it.slice(1 until args.product.colors?.size!!)) }
        args.product.sizes?.let { sizes.addAll(it.slice(1 until args.product.sizes?.size!!)) }
        name.text = args.product.name
        description.text = args.product.about
        price.text = "${args.product.price}$"
        carousel.setImageListener(imageListener)
        carousel.pageCount = args.product.imagesUrls!!.size
        val adapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, colors)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        colorSpinner.adapter = adapter
        val adapterSize: ArrayAdapter<String> =
            ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, sizes)
        adapterSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sizeSpinner.adapter = adapterSize

        favoriteDetail.setOnClickListener {
            viewModel.onFavoriteProduct(args.product)
            favoriteDetail.setColorFilter(Color.parseColor("#FF4500"))

        }

        addToBag.setOnClickListener {
            viewModel.addProductToBag(args.product,colorSpinner.selectedItem.toString(),sizeSpinner.selectedItem.toString())
            Toast.makeText(
                requireContext(),
                "Your product successfully added to bag",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private var imageListener: ImageListener =
        ImageListener { position, imageView -> // You can use Glide or Picasso here
            imageView.scaleType = ImageView.ScaleType.FIT_CENTER;
            Glide.with(requireContext()).load(args.product.imagesUrls!![position]).into(imageView)
        }
}