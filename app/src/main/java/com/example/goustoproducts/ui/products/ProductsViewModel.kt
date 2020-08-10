package com.example.goustoproducts.ui.products

import androidx.lifecycle.ViewModel
import com.example.goustoproducts.api.products.IProductsAPI
import com.example.goustoproducts.api.products.model.ProductData
import com.example.goustoproducts.api.products.model.ProductsResponse
import com.example.goustoproducts.database.DBProductData
import com.example.goustoproducts.database.IProductDataDao
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class ProductsViewModel(private val api: IProductsAPI) : ViewModel() {

    var products: List<ProductData>? = null

    fun getProducts(): Single<ProductsResponse> =
        api.fetchProductList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { products = it.data }
            .doOnError { println(it.message) }

    fun populateDatabase(dao: IProductDataDao, productData: List<ProductData>) {
        productData.forEach {
            val data = DBProductData(
                id = it.id!!,
                title = it.title,
                price = it.listPrice,
                description = it.description
            )
            dao.insertAll(data)
        }
    }

    fun getProductsFromDatabase(dao: IProductDataDao): List<ProductData> {
        val products = ArrayList<ProductData>()
        val dbProducts = dao.getAll()
        dbProducts.forEach {
            val data = ProductData(
                id = it.id,
                title = it.title,
                listPrice = it.price,
                description = it.description
            )
            products.add(data)
        }
        return products
    }
}
