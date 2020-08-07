package com.example.goustoproducts.ui.products

import androidx.lifecycle.ViewModel
import com.example.goustoproducts.api.products.IProductsAPI
import com.example.goustoproducts.api.products.model.ProductsResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ProductsViewModel(private val api: IProductsAPI) : ViewModel() {

    var products: ProductsResponse? = null

    fun getProducts(): Single<ProductsResponse> =
        api.fetchProductList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { products = it }
            .doOnError { println(it.message) }
}
