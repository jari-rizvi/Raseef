package com.teamx.raseef.dataclasses.products

import com.teamx.raseef.dataclasses.Shop

import androidx.annotation.Keep


@Keep
data class Doc(
    val __v: Int,
    val _id: String,
    val categories: List<Category>,
    val createdAt: String,
    val description: String,
    val gallery: List<String>,
    val height: String,
    val image: String,
    val in_stock: Boolean,
    val is_taxable: Boolean,
    val length: String,
    val max_price: Int,
    val min_price: Int,
    val name: String,
    val orders: List<Any>,
    val price: Int,
    val product_type: String,
    val quantity: Int,
    val rating_count: List<Any>,
    val ratings: Int,
    val sale_price: Any,
    val shop: Shop,
    val sku: String,
    val slug: String,
    val status: String,
    val tags: List<Any>,
    val type: Type,
    val unit: String,
    val updatedAt: String,
    val variation_options: List<VariationOption>,
    val variations: List<Variation>,
    val width: String
)