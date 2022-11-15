package com.teamx.raseef.dataclasses.productid

import com.teamx.raseef.dataclasses.Shop
import com.teamx.raseef.dataclasses.VariationOption

import androidx.annotation.Keep

 
@Keep
data class ProductVariable(
    val __v: Int,
    val _id: String,
    val categories: List<Category>,
    val createdAt: String,
    val description: String,
    val gallery: List<String>,
    val image: String,
    val in_stock: Boolean,
    val is_taxable: Boolean,
    val max_price: Int,
    val min_price: Int,
    val name: String,
    val orders: List<String>,
    val product_type: String,
    val quantity: Int,
    val rating_count: List<RatingCount>,
    val ratings: Int,
    val shop: Shop,
    val slug: String,
    val status: String,
    val tags: List<Tag>,
    val type: Type,
    val unit: String,
    val updatedAt: String,
    val variation_options: List<VariationOption>,
    val variations: List<Variation>
)