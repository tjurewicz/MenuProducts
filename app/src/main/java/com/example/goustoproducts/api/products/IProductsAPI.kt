package com.example.goustoproducts.api.products

import com.example.goustoproducts.api.products.model.ProductsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface IProductsAPI {

    companion object {
        const val API_PRODUCTS_ENDPOINT = "products"
    }

    @GET(API_PRODUCTS_ENDPOINT)
    fun fetchProductList(
        @Query("includes[]") categories: String,
        @Query("includes[]") attributes: String,
        @Query("image_sizes[]") imagesSizes: String
    ): Single<ProductsResponse>

}
