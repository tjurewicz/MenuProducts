package com.example.goustoproducts.api.products.model

import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("unit") val unit: String?,
    @SerializedName("value") val value: String
)
