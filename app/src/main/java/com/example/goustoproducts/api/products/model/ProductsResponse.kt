package com.example.goustoproducts.api.products.model

import com.example.goustoproducts.api.products.model.ProductInformation
import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("status") val status: String,
    @SerializedName("meta") val meta: MetaData,
    @SerializedName("data") val data: List<ProductInformation>
)
