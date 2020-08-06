package com.example.goustoproducts.api.products.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("box_limit") val boxLimit: Int?,
    @SerializedName("is_default") val isDefault: Boolean?,
    @SerializedName("recently_added") val recentlyAdded: Boolean?,
    @SerializedName("hidden") val hidden: Boolean?,
    @SerializedName("pivot") val pivot: Pivot
)
