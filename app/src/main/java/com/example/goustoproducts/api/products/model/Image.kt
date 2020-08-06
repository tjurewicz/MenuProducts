package com.example.goustoproducts.api.products.model

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("src") val offset: String,
    @SerializedName("url") val limit: String,
    @SerializedName("width") val count: Int
)