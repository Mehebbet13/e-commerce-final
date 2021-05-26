import com.example.e_commerce.ui.AllProductsFragmentEvent

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_commerce.Product
import com.example.e_commerce.SingleLiveData


class AppViewModel : ViewModel() {

    var favProducts = arrayListOf<Product>()
    var bagProductsArray = arrayListOf<Product>()
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products
    private val _size = MutableLiveData<String>()
    val size: LiveData<String>
        get() = _size
    private val _color = MutableLiveData<String>()
    val color: LiveData<String>
        get() = _color

    private val _bagProducts = MutableLiveData<List<Product>>()
    val bagProducts: LiveData<List<Product>>
        get() = _bagProducts

    val event = SingleLiveData<AllProductsFragmentEvent>()
    fun onClickedProduct(product: Product) {
        event.postValue(
            AllProductsFragmentEvent.OpenProductDetail(product)
        )
    }

    fun setSize(size: String) {
        _size.postValue(size)
    }

    fun setColor(color: String) {
        _size.postValue(color)
    }

    fun onFavoriteProduct(product: Product) {
        Log.e("fav","fav")
        Log.e("fav",product.fav.toString())
//        if (product.fav) {
//            favProducts.remove(product)
//            _products.postValue(
//                favProducts
//            )
//            product.fav = false
//        } else {
            favProducts.add(product)
            _products.postValue(
                favProducts
            )

            product.sizeSelected = null
            product.color = null
            product.fav = true
        Log.e("fav",product.fav.toString())

//        }
    }

    fun addProductToBag(product: Product, color: String, size: String) {
        Log.e("co", bagProductsArray.toString())
            bagProductsArray.add(product)
            _bagProducts.postValue(
                bagProductsArray
            )
            product.sizeSelected = size
            product.color = color
            product.inBag = true
    }

    fun deleteBagProduct(product: Product) {
        bagProductsArray.remove(product)
        _bagProducts.postValue(
            bagProductsArray
        )
        product.inBag = false
    }

  fun deleteFavProduct(product: Product) {
      favProducts.remove(product)
      _products.postValue(
          favProducts
      )
      product.fav = false
    }

    fun onClearAllProducts() {
        favProducts.clear()
        _products.postValue(
            favProducts
        )
    }
}