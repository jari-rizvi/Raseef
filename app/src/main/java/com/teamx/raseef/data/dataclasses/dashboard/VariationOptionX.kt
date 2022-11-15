package com.teamx.raseef.data.dataclasses.dashboard

data class VariationOptionX(
    val _id: String,
    val createdAt: String,
    val is_disable: Boolean,
    val price: Int,
    val quantity: Int,
    val sale_price: Int,
    val sku: String,
    val title: String,
    val updatedAt: String,
    val variation_images: List<String>
)