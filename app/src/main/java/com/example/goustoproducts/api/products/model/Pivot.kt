package com.example.goustoproducts.api.products.model

import com.google.gson.annotations.SerializedName

data class Pivot(
    @SerializedName("created_at") val createdAt: String
)