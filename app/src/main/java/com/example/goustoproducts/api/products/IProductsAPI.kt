package com.example.goustoproducts.api.products

import retrofit2.Call
import retrofit2.http.GET

interface IProductsAPI {

    companion object {
        const val API_PRODUCTS_ENDPOINT = "/products"
    }

    @GET(API_PRODUCTS_ENDPOINT)
    fun fetchProductList() : Call<ProductsResponse>

}