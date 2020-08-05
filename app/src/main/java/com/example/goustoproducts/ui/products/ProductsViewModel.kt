package com.example.goustoproducts.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goustoproducts.api.products.IProductsAPI
import com.example.goustoproducts.api.products.model.ProductsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsViewModel(val api: IProductsAPI) : ViewModel() {

    fun requestProducts() {
        var model: ProductsResponse? = null
        viewModelScope.launch(Dispatchers.IO) {
            model = api.fetchProductList()
            println("vm $model")
        }
    }
}
