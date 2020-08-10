package com.example.goustoproducts.api.products.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductData(
    @SerializedName("id") val id: String? = null,
    @SerializedName("sku") val sku: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("list_price") val listPrice: String? = null,
    @SerializedName("is_vatable") val isVatable: Boolean? = null,
    @SerializedName("is_for_sale") val isForSale: Boolean? = null,
    @SerializedName("age_restricted") val ageRestricted: String? = null,
    @SerializedName("box_limit") val boxLimit: Int? = null,
    @SerializedName("always_on_menu") val alwaysOnMenu: Boolean? = null,
    @SerializedName("volume") val volume: Int? = null,
    @SerializedName("zone") val zone: String? = null,
    @SerializedName("created_at") val createdAt: String? = null,
    @SerializedName("categories") val categories: List<Category>? = null,
    @SerializedName("attributes") val attributes: List<Attributes>? = null,
    @SerializedName("tags") val tags: List<String>? = null,
    @SerializedName("images") val images: Image? = null
) : Serializable
