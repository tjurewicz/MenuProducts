package com.example.goustoproducts.api.products.model

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("750") val imageDetails: ImageDetails
)

data class ImageDetails(
    @SerializedName("src") val src: String,
    @SerializedName("url") val url: String,
    @SerializedName("width") val width: Int
)
