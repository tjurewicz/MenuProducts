package com.example.goustoproducts.api.products

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("data") val data: List<ProductInformation>
)
