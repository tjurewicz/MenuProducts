package com.example.goustoproducts.api.products.model

import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("offset") val offset: Int,
    @SerializedName("limit") val limit: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("total") val total: Int
)
