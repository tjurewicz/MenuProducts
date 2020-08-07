package com.example.goustoproducts.api.products.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductData(
    @SerializedName("id") val id: String,
    @SerializedName("sku") val sku: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("list_price") val listPrice: String,
    @SerializedName("is_vatable") val isVatable: Boolean,
    @SerializedName("is_for_sale") val isForSale: Boolean,
    @SerializedName("age_restricted") val ageRestricted: String,
    @SerializedName("box_limit") val boxLimit: Int?,
    @SerializedName("always_on_menu") val alwaysOnMenu: Boolean,
    @SerializedName("volume") val volume: Int?,
    @SerializedName("zone") val zone: String?,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("categories") val categories: List<Category>,
    @SerializedName("attributes") val attributes: List<Attributes>?,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("images") val images: Image
) : Serializable
