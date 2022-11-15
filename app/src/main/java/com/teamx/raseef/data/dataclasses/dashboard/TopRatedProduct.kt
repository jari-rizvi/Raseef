package com.teamx.raseef.data.dataclasses.dashboard

import com.teamx.raseef.dataclasses.allorders.RatingCountX

data class TopRatedProduct(
    val __v: Int,
    val _id: String,
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
    val orders: List<String>,
    val price: Int,
    val product_type: String,
    val quantity: Int,
    val rating_count: List<RatingCountX>,
    val ratings: Int,
    val sale_price: Int,
    val shop: ShopX,
    val sku: String,
    val slug: String,
    val status: String,
    val type: TypeX,
    val unit: String,
    val updatedAt: String,
    val variation_options: List<VariationOptionX>,
    val width: String
)