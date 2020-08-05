package com.example.goustoproducts.api.products

import com.example.goustoproducts.api.products.model.ProductsResponse
import retrofit2.http.GET

interface IProductsAPI {

    companion object {
        const val API_PRODUCTS_ENDPOINT = "products"
    }

    @GET(API_PRODUCTS_ENDPOINT)
    suspend fun fetchProductList(): ProductsResponse

}
