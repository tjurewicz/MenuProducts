package com.example.goustoproducts.api.products.model

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("status") val status: String,
    @SerializedName("meta") val meta: Metadata,
    @SerializedName("data") val data: List<ProductInformation>
)
